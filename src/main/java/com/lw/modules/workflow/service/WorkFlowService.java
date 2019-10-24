package com.lw.modules.workflow.service;

import com.lw.common.service.BaseService;
import com.lw.common.utils.StringUtils;
import com.lw.modules.sys.dao.RoleDao;
import com.lw.modules.sys.dao.UserDao;
import com.lw.modules.sys.entity.Office;
import com.lw.modules.sys.entity.Role;
import com.lw.modules.sys.entity.User;
import com.lw.modules.sys.utils.UserUtils;
import com.lw.modules.workflow.dao.TBaActivityDao;
import com.lw.modules.workflow.dao.TBaNoteDao;
import com.lw.modules.workflow.dao.TBaTaskDao;
import com.lw.modules.workflow.dao.TBaTempNoteDao;
import com.lw.modules.workflow.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 任务记录Service
 *
 * @author 张旭东
 * @version 2015-09-01
 */
@Service
@Transactional(readOnly = true)
public class WorkFlowService extends BaseService {
    private String applyState = "01";//申请状态：01已保存，未提交02已提交03 04已受理05
    private String status = "1";//流程实例状态:1运行中2结束3终止4暂停
    private String SUCCESS = "1";//成功
    private String FAIL = "0";//失败

    @Autowired
    private TBaActivityDao tBaActivityDao;

    @Autowired
    private TBaNoteDao tBaNoteDao;

    @Autowired
    private TBaTaskDao tBaTaskDao;

    @Autowired
    private TBaTempNoteDao tBaTempNoteDao;

    @Autowired
    private TBaTempActivityService tBaTempActivityService;

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    /**
     * 功能描述：创建流程(确认)
     * 调用位置：
     * 适用数据：
     * 更新时间：2018-9-10
     * 更新人员：handf
     */
    @Transactional(readOnly = false)
    public String createWorkFlow(String logo, String applyId, String companyId, String activityName, String terminationReason) throws Exception {
        try {
            //根据受理机构和流程标识符查询出对应的流程定义模板
            //查询流程模板定义表
            TBaTempActivity tBaTempActivity = new TBaTempActivity();
            tBaTempActivity.setLogo(logo);
            Office office = new Office();
            office.setId(companyId);
            tBaTempActivity.setCompany(office);
            tBaTempActivity = tBaTempActivityService.getByTBaTempActivity(tBaTempActivity);
            if (tBaTempActivity == null) {
                return FAIL;
            }
            //1、保存流程实例表
            TBaActivity tBaActivity = new TBaActivity();
            tBaActivity.preInsert();
            tBaActivity.setTempActivityId(tBaTempActivity.getId());
            applyState = "01";//已保存未提交
            tBaActivity.setApplyState(applyState);
            tBaActivity.setApplyId(applyId);//申请ID
            status = "1";//运行中
            tBaActivity.setStatus(status);
            if (activityName == null || activityName.equals("")) {
                activityName = tBaTempActivity.getActivityName();
            }
            tBaActivity.setActivityName(activityName);
            tBaActivity.setCompany(tBaTempActivity.getCompany());//提交机构
            tBaActivity.setUser(UserUtils.getUser());
            tBaActivity.setTerminationReason(terminationReason);
            tBaActivityDao.insert(tBaActivity);
            //获取第一个有效节点模板信息
            List<TBaTempNote> tBaTempNoteList = tBaTempActivity.getTBaTempNoteList();
            TBaTempNote tBaTempNote = new TBaTempNote();
            if (null != tBaTempNoteList) {
                for (TBaTempNote tBaTempNoteTemp : tBaTempNoteList) {
                    if ("1".equals(tBaTempNoteTemp.getStatus())) {
                        tBaTempNote = tBaTempNoteTemp;
                        break;
                    }
                }
            }

            //2、保存节点表
            TBaNote tBaNote = new TBaNote();
            tBaNote.preInsert();
            tBaNote.setTempNoteId(tBaTempNote.getId());
            tBaNote.setActivityId(tBaActivity);
            tBaNote.setName(tBaTempNote.getName());
            status = "1";//运行中
            tBaNote.setStatus(status);
            tBaNoteDao.insert(tBaNote);
            //3、保存任务表
            TBaTask tBaTask = new TBaTask();
            tBaTask.preInsert();
            tBaTask.setActivityId(tBaActivity.getId());
            tBaTask.setNoteId(tBaNote.getId());
            tBaTask.setNoteName(tBaNote.getName());
            tBaTask.setUpdateName(UserUtils.getUser().getName());
            tBaTask.setCreateName(UserUtils.getUser().getName());
            status = "1";//运行中
            tBaTask.setStatus(status);
            tBaTask.setDualType("01");
            tBaTaskDao.insert(tBaTask);
            return SUCCESS;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new Exception();
        }
    }

    /**
     * 结束本节点 并发送到下一节点(cma)
     *
     * @param tBaTask
     */
    @Transactional(readOnly = false)
    public List<User> sendNoteTaskAll(TBaTask tBaTask) {
        //流程节点模板中查询下一节点(tempNoteId)
        //必须在对t_ba_note表数据添加前，获得下一节点，否则查询语句失效
        String tempNoteId = "";
        if (StringUtils.isEmpty(tBaTask.getNextTempNoteId())) {
            TBaTempNote tBaTempNote = getTBaTempNote(tBaTask.getActivityId());
            tempNoteId = tBaTempNote.getId();
        } else {
            tempNoteId = tBaTask.getNextTempNoteId();
        }

        //1、改变流程实例的状态，改为提交
        TBaActivity tBaActivity = tBaActivityDao.get(tBaTask.getActivityId());
        //2、修改当前节点状态
        TBaNote tBaNote = tBaNoteDao.get(tBaTask.getNoteId());
        tBaNote.preUpdate();//更新tBaNote信息
        status = "2"; // 已完成
        tBaNote.setStatus(status);
        tBaNoteDao.update(tBaNote);
        //3、把当前用户任务记录状态为运行中的记录改为已完成
        User user = UserUtils.getUser();
        if(tBaTask.getUpdateBy() != null ){
            tBaTask.setFormerTransactor(tBaTask.getUpdateBy().getId());
        }
        tBaTask.preUpdate();
        status = "2"; // 已完成
        tBaTask.setDualType("02");
        tBaTask.setStatus(status);
        tBaTaskDao.update(tBaTask);
        //4、删除当前状态为运行中的任务记录
        tBaTaskDao.deleteOthersTask(tBaTask);
        //5、具体流程中添加下一个节点
        TBaTempNote tBaTempNote = tBaTempNoteDao.get(tempNoteId);
        TBaNote tBaNoteNext = new TBaNote();
        tBaNoteNext.preInsert();
        tBaNoteNext.setTempNoteId(tBaTempNote.getId());

        // 获得下一个节点和当前节点的权重
        String nextPriority = getNextPriority(tempNoteId, "0", tBaTask.getId());

        tBaNoteNext.setActivityId(tBaActivity);
        tBaNoteNext.setName(tBaTempNote.getName());
        status = "1";//运行中
        tBaNoteNext.setStatus(status);
        tBaNoteDao.insert(tBaNoteNext);
        //6、创建任务记录（创建的记录数是和人员数是一一对应的）
        User userPara = new User();
        userPara.setCompany(tBaActivity.getCompany());
        TBaTask tBaTaskNext = new TBaTask();
        tBaTaskNext.setActivityId(tBaActivity.getId());
        tBaTaskNext.setNoteId(tBaNoteNext.getId());
        tBaTaskNext.setNoteName(tBaNoteNext.getName());
        tBaTaskNext.setCreateName(user.getName());
        status = "1";//运行中
        tBaTaskNext.setStatus(status);

        //发送给下一节点所有人
        List<User> userList = new ArrayList<User>();
        for (User userTemp : userList) {
            tBaTaskNext.preInsert();
            tBaTaskNext.setUpdateBy(userTemp);
            tBaTaskNext.setUpdateName(userTemp.getName());
            tBaTaskNext.setLastTaskId(tBaTask.getId());
            tBaTaskNext.setDualType("01");
            if (StringUtils.isNotBlank(nextPriority)) {
                tBaTaskNext.setPriority(nextPriority);
            }
            tBaTaskDao.insert(tBaTaskNext);
        }
        return userList;
    }

    @Transactional(readOnly = false)
    public void sendNoteTaskAll(TBaTask tBaTask, String userId) {
        //流程节点模板中查询下一节点(tempNoteId)
        //必须在对t_ba_note表数据添加前，获得下一节点，否则查询语句失效
        String tempNoteId = "";
        if (StringUtils.isEmpty(tBaTask.getNextTempNoteId())) {
            TBaTempNote tBaTempNote = getTBaTempNote(tBaTask.getActivityId());
            tempNoteId = tBaTempNote.getId();
        } else {
            tempNoteId = tBaTask.getNextTempNoteId();
        }

        //1、改变流程实例的状态，改为提交
        TBaActivity tBaActivity = tBaActivityDao.get(tBaTask.getActivityId());
        //2、修改当前节点状态
        TBaNote tBaNote = tBaNoteDao.get(tBaTask.getNoteId());
        tBaNote.preUpdate();//更新tBaNote信息
        status = "2"; // 已完成
        tBaNote.setStatus(status);
        tBaNoteDao.update(tBaNote);
        //3、把当前用户任务记录状态为运行中的记录改为已完成
        User user = UserUtils.get(userId);
        tBaTask.setFormerTransactor(tBaTask.getUpdateBy().getId());
        tBaTask.preUpdate();
        status = "2"; // 已完成
        tBaTask.setDualType("02");
        tBaTask.setStatus(status);
        tBaTaskDao.update(tBaTask);
        //4、删除当前状态为运行中的任务记录
        tBaTaskDao.deleteOthersTask(tBaTask);
        //5、具体流程中添加下一个节点
        TBaTempNote tBaTempNote = tBaTempNoteDao.get(tempNoteId);
        TBaNote tBaNoteNext = new TBaNote();
        tBaNoteNext.preInsert();
        tBaNoteNext.setTempNoteId(tBaTempNote.getId());

        // 获得下一个节点和当前节点的权重
        String nextPriority = getNextPriority(tBaNote.getTempNoteId(), "0", tBaTask.getId());

        tBaNoteNext.setActivityId(tBaActivity);
        tBaNoteNext.setName(tBaTempNote.getName());
        status = "1";//运行中
        tBaNoteNext.setStatus(status);
        tBaNoteDao.insert(tBaNoteNext);
        //6、创建任务记录（创建的记录数是和人员数是一一对应的）
        User userPara = new User();
        userPara.setCompany(tBaActivity.getCompany());
        TBaTask tBaTaskNext = new TBaTask();
        tBaTaskNext.setActivityId(tBaActivity.getId());
        tBaTaskNext.setNoteId(tBaNoteNext.getId());
        tBaTaskNext.setNoteName(tBaNoteNext.getName());
        tBaTaskNext.setCreateName(user.getName());
        status = "1";//运行中
        tBaTaskNext.setStatus(status);

        //发送给下一节点所有人
        List<User> userList = new ArrayList<User>();
        for (User userTemp : userList) {
            tBaTaskNext.preInsert();
            tBaTaskNext.setUpdateBy(userTemp);
            tBaTaskNext.setUpdateName(userTemp.getName());
            tBaTaskNext.setLastTaskId(tBaTask.getId());
            tBaTaskNext.setDualType("01");
            if (StringUtils.isNotBlank(nextPriority)) {
                tBaTaskNext.setPriority(nextPriority);
            }
            tBaTaskDao.insert(tBaTaskNext);
        }
    }

    /**
     * 发送到下一节点指定人
     *
     * @param tBaTask
     */
    @Transactional(readOnly = false)
    public List<User> sendNoteTask(TBaTask tBaTask) {
        List<User> users = new ArrayList<User>();
        TBaActivity tBaActivity = tBaActivityDao.get(tBaTask.getActivityId());
        String[] userIds = tBaTask.getNextPerson().split(",");
        String tempNoteId = "";
        if (StringUtils.isEmpty(tBaTask.getNextTempNoteId())) {
            TBaTempNote tBaTempNote = getTBaTempNote(tBaTask.getActivityId());
            tempNoteId = tBaTempNote.getId();
        } else {
            tempNoteId = tBaTask.getNextTempNoteId();
        }
        // 1、当前结点为运行中的改为已完成
        TBaNote tBaNote = tBaNoteDao.get(tBaTask.getNoteId());
        tBaNote.preUpdate(); // 更新tBaNote信息
        status = "2"; // 已完成
        tBaNote.setStatus(status);
        tBaNoteDao.update(tBaNote);
        // 2、把当前用户任务记录状态为运行中的记录改为已完成
        tBaTask.setFormerTransactor(tBaTask.getUpdateBy().getId());
        tBaTask.preUpdate();
        status = "2"; // 已完成
        tBaTask.setDualType("02");
        tBaTask.setStatus(status);
        tBaTaskDao.update(tBaTask);
        // 3、删除当前状态为运行中的任务记录
        tBaTaskDao.deleteOthersTask(tBaTask);
        //4、创建新节点
        TBaTempNote tBaTempNote = tBaTempNoteDao.get(tempNoteId);
        TBaNote tBaNoteNext = new TBaNote();
        tBaNoteNext.preInsert();
        tBaNoteNext.setTempNoteId(tBaTempNote.getId());
        tBaNoteNext.setActivityId(tBaActivity);
        // 获得下一个节点和当前节点的权重
        String nextPriority = getNextPriority(tBaNote.getTempNoteId(), "0", tBaTask.getId());

        tBaNoteNext.setName(tBaTempNote.getName());
        status = "1";//运行中
        tBaNoteNext.setStatus(status);
        tBaNoteDao.insert(tBaNoteNext);
        //6、创建任务记录（这边根据直接发送给userId）

        for (int i = 0; i < userIds.length; i++) {
            if (StringUtils.isNotEmpty(userIds[i])) {
                User userNext = userDao.get(userIds[i]);
                users.add(userNext);
                User user = UserUtils.getUser();
                TBaTask tBaTaskNext = new TBaTask();
                tBaTaskNext.preInsert();
                tBaTaskNext.setActivityId(tBaActivity.getId());
                tBaTaskNext.setNoteId(tBaNoteNext.getId());
                tBaTaskNext.setNoteName(tBaNoteNext.getName());
                tBaTaskNext.setCreateName(user.getName());
                tBaTaskNext.setUpdateBy(userNext);
                status = "1";//运行中
                tBaTaskNext.setStatus(status);
                if (StringUtils.isNotBlank(nextPriority)) {
                    tBaTaskNext.setPriority(nextPriority);
                }
                tBaTaskNext.setLastTaskId(tBaTask.getId());
                tBaTaskNext.setUpdateName(userNext.getName());
                tBaTaskNext.setDualType("01");
                tBaTaskDao.insert(tBaTaskNext);
            }
        }
        return users;
    }

    @Transactional(readOnly = false)
    public void sendNoteTask(TBaTask tBaTask, String userId) {
        TBaActivity tBaActivity = tBaActivityDao.get(tBaTask.getActivityId());
        String[] userIds = tBaTask.getNextPerson().split(",");
        String tempNoteId = "";
        if (StringUtils.isEmpty(tBaTask.getNextTempNoteId())) {
            TBaTempNote tBaTempNote = getTBaTempNote(tBaTask.getActivityId());
            tempNoteId = tBaTempNote.getId();
        } else {
            tempNoteId = tBaTask.getNextTempNoteId();
        }
        // 1、当前结点为运行中的改为已完成
        TBaNote tBaNote = tBaNoteDao.get(tBaTask.getNoteId());
        tBaNote.preUpdate(); // 更新tBaNote信息
        status = "2"; // 已完成
        tBaNote.setStatus(status);
        tBaNoteDao.update(tBaNote);
        // 2、把当前用户任务记录状态为运行中的记录改为已完成
        tBaTask.setFormerTransactor(tBaTask.getUpdateBy().getId());
        tBaTask.preUpdate();
        status = "2"; // 已完成
        tBaTask.setDualType("02");
        tBaTask.setStatus(status);
        tBaTaskDao.update(tBaTask);
        // 3、删除当前状态为运行中的任务记录
        tBaTaskDao.deleteOthersTask(tBaTask);
        //4、创建新节点
        TBaTempNote tBaTempNote = tBaTempNoteDao.get(tempNoteId);
        TBaNote tBaNoteNext = new TBaNote();
        tBaNoteNext.preInsert();
        tBaNoteNext.setTempNoteId(tBaTempNote.getId());
        tBaNoteNext.setActivityId(tBaActivity);
        // 获得下一个节点和当前节点的权重
        Map<String, String> condition = new HashMap<String, String>();

        String nextPriority = getNextPriority(tBaNote.getTempNoteId(), "0", tBaTask.getId());

        tBaNoteNext.setName(tBaTempNote.getName());
        status = "1";//运行中
        tBaNoteNext.setStatus(status);
        tBaNoteDao.insert(tBaNoteNext);
        //6、创建任务记录（这边根据直接发送给userId）

        for (int i = 0; i < userIds.length; i++) {
            if (StringUtils.isNotEmpty(userIds[i])) {
                User userNext = userDao.get(userIds[i]);
                User user = UserUtils.get(userId);
                TBaTask tBaTaskNext = new TBaTask();
                tBaTaskNext.preInsert();
                tBaTaskNext.setActivityId(tBaActivity.getId());
                tBaTaskNext.setNoteId(tBaNoteNext.getId());
                tBaTaskNext.setNoteName(tBaNoteNext.getName());
                tBaTaskNext.setCreateName(user.getName());
                tBaTaskNext.setUpdateBy(userNext);
                status = "1";//运行中
                tBaTaskNext.setStatus(status);
                if (StringUtils.isNotBlank(nextPriority)) {
                    tBaTaskNext.setPriority(nextPriority);
                }
                tBaTaskNext.setLastTaskId(tBaTask.getId());
                tBaTaskNext.setUpdateName(userNext.getName());
                tBaTaskNext.setDualType("01");
                tBaTaskDao.insert(tBaTaskNext);
            }
        }

    }

    /**
     * 岗位内流转（已经确认）
     *
     * @param tBaTask
     */
    @Transactional(readOnly = false)
    public List<User> sendTask(TBaTask tBaTask) {
        List<User> users = new ArrayList<User>();
        //1、把当前用户任务记录状态为运行中的记录改为已完成
        TBaActivity tBaActivity = tBaActivityDao.get(tBaTask.getActivityId());
        tBaTask.setFormerTransactor(tBaTask.getUpdateBy().getId());
        tBaTask.preUpdate();
        status = "2"; // 已完成
        tBaTask.setStatus(status);
        tBaTaskDao.update(tBaTask);

        TBaNote tBaNote = tBaNoteDao.get(tBaTask.getNoteId());

        // 获得下一个节点和当前节点的权重
        String priority = tBaTask.getPriority();
        if (StringUtils.isBlank(priority)) {
            priority = "0";
        }

        String nextPriority = getNextPriority(tBaNote.getTempNoteId(), priority, tBaTask.getId());
        if (!"0".equals(priority) && StringUtils.isBlank(nextPriority)) {
            nextPriority = priority;
        }

        //2、删除当前状态为运行中的任务记录
        tBaTaskDao.deleteOthersTask(tBaTask);
        //3、创建任务记录
        User userNext = userDao.get(tBaTask.getNextPerson());
        User user = UserUtils.getUser();
        TBaTask tBaTaskNext = new TBaTask();
        tBaTaskNext.preInsert();
        tBaTaskNext.setActivityId(tBaActivity.getId());
        tBaTaskNext.setNoteId(tBaTask.getNoteId());
        tBaTaskNext.setNoteName(tBaTask.getNoteName());
        tBaTaskNext.setCreateName(user.getName());
        status = "1";//运行中
        tBaTaskNext.setStatus(status);
        tBaTaskNext.setUpdateBy(userNext);
        tBaTaskNext.setDualType("01");
        users.add(userNext);
        if (StringUtils.isNotBlank(nextPriority)) {
            tBaTaskNext.setPriority(nextPriority);
        }
        tBaTaskNext.setLastTaskId(tBaTask.getId());
        tBaTaskNext.setUpdateName(userNext.getName());
        tBaTaskDao.insert(tBaTaskNext);
        return users;
    }

    @Transactional(readOnly = false)
    public void sendTask(TBaTask tBaTask, String userId) {
        //1、把当前用户任务记录状态为运行中的记录改为已完成
        TBaActivity tBaActivity = tBaActivityDao.get(tBaTask.getActivityId());
        tBaTask.setFormerTransactor(tBaTask.getUpdateBy().getId());
        tBaTask.preUpdate();
        status = "2"; // 已完成
        tBaTask.setStatus(status);
        tBaTaskDao.update(tBaTask);

        TBaNote tBaNote = tBaNoteDao.get(tBaTask.getNoteId());

        // 获得下一个节点和当前节点的权重
        String priority = tBaTask.getPriority();
        if (StringUtils.isBlank(priority)) {
            priority = "0";
        }

        String nextPriority = getNextPriority(tBaNote.getTempNoteId(), priority, tBaTask.getId());

        //2、删除当前状态为运行中的任务记录
        tBaTaskDao.deleteOthersTask(tBaTask);
        //3、创建任务记录
        User userNext = userDao.get(tBaTask.getNextPerson());
        User user = UserUtils.get(userId);
        TBaTask tBaTaskNext = new TBaTask();
        tBaTaskNext.preInsert();
        tBaTaskNext.setActivityId(tBaActivity.getId());
        tBaTaskNext.setNoteId(tBaTask.getNoteId());
        tBaTaskNext.setNoteName(tBaTask.getNoteName());
        tBaTaskNext.setCreateName(user.getName());
        status = "1";//运行中
        tBaTaskNext.setStatus(status);
        tBaTaskNext.setUpdateBy(userNext);
        tBaTaskNext.setDualType("01");
        if (StringUtils.isNotBlank(nextPriority)) {
            tBaTaskNext.setPriority(nextPriority);
        }
        tBaTaskNext.setLastTaskId(tBaTask.getId());
        tBaTaskNext.setUpdateName(userNext.getName());
        tBaTaskDao.insert(tBaTaskNext);
    }

    /**
     * 流程撤回（谁发送的谁撤回，但是如果已经处理就无法撤回 1 成功 2 已处理无法撤回）
     */
    @Transactional(readOnly = false)
    //public int getBack(String activityId,String noteId,String currentUserId){
    public int getBack(TBaTask tBaTaskOld) {
        int result = 2;//状态1：正常撤回;状态2：撤回失败
        //实例
        TBaActivity tBaActivity = tBaActivityDao.get(tBaTaskOld.getActivityId());
        tBaActivity.setApplyState("01");

        //当前节点对象
        TBaNote tBaNote = new TBaNote();
        tBaNote.setActivityId(tBaActivity);
        tBaNote.setStatus("1");
        tBaNote = tBaNoteDao.getTBaNote(tBaNote);
        //当前未处理任务集合
        TBaTask tBaTask = new TBaTask();
        tBaTask.setActivityId(tBaActivity.getId());
        tBaTask.setStatus("1");
        List<TBaTask> tBaTaskList = tBaTaskDao.getTBaTaskList(tBaTask);
        String lastTaskId = tBaTaskList.get(0).getLastTaskId();//上一任务的ID
        String userId = tBaTaskList.get(0).getCreateBy().getId();//上一任务的处理人的ID
        //上一任务
        TBaTask tBaTaskLast = tBaTaskDao.get(lastTaskId);
        //上一节点
        TBaNote tBaNoteLast = tBaNoteDao.get(tBaTaskLast.getNoteId());
        //判断当前用户是不是上一处理任务的人员
        if (!userId.equals(tBaTaskOld.getUpdateBy().getId())) {
            return result;
        }

        tBaActivityDao.update(tBaActivity);
        if (tBaNote.getId().equals(tBaNoteLast.getId())) {//节点相同=岗位内流转
            //1、删除当前任务，修改上一任务状态01
            tBaTaskDao.delete(tBaTaskList.get(0));
        } else {//下一节点，非岗位内流转
            TBaTask tBaTaskNew = new TBaTask();
            tBaTaskNew.setNoteId(tBaNote.getId());
            tBaTaskDao.delete(tBaTaskNew);//删除当前结点下面的任务记录
            tBaNoteDao.delete(tBaNote);//删除当前结点
            tBaNoteLast.setStatus("1");
            tBaNoteDao.update(tBaNoteLast);//把上一节点改为有效节点
        }
        tBaTaskLast.setStatus("1");
        tBaTaskDao.update(tBaTaskLast);
        result = 1;
        return result;
    }

    public int getBack(TBaTask tBaTaskOld, String applyState) {
        int result = 2;//状态1：正常撤回;状态2：撤回失败
        //实例
        TBaActivity tBaActivity = tBaActivityDao.get(tBaTaskOld.getActivityId());
        tBaActivity.setApplyState(applyState);

        //当前节点对象
        TBaNote tBaNote = new TBaNote();
        tBaNote.setActivityId(tBaActivity);
        tBaNote.setStatus("1");
        tBaNote = tBaNoteDao.getTBaNote(tBaNote);
        //当前未处理任务集合
        TBaTask tBaTask = new TBaTask();
        tBaTask.setActivityId(tBaActivity.getId());
        tBaTask.setStatus("1");
        List<TBaTask> tBaTaskList = tBaTaskDao.getTBaTaskList(tBaTask);
        String lastTaskId = tBaTaskList.get(0).getLastTaskId();//上一任务的ID
        String userId = tBaTaskList.get(0).getCreateBy().getId();//上一任务的处理人的ID
        //上一任务
        TBaTask tBaTaskLast = tBaTaskDao.get(lastTaskId);
        //上一节点
        TBaNote tBaNoteLast = tBaNoteDao.get(tBaTaskLast.getNoteId());
        //判断当前用户是不是上一处理任务的人员
        if (!userId.equals(tBaTaskOld.getUpdateBy().getId())) {
            return result;
        }

        tBaActivityDao.update(tBaActivity);
        if (tBaNote.getId().equals(tBaNoteLast.getId())) {//节点相同=岗位内流转
            //1、删除当前任务，修改上一任务状态01
            tBaTaskDao.delete(tBaTaskList.get(0));
        } else {//下一节点，非岗位内流转
            TBaTask tBaTaskNew = new TBaTask();
            tBaTaskNew.setNoteId(tBaNote.getId());
            tBaTaskDao.delete(tBaTaskNew);//删除当前结点下面的任务记录
            tBaNoteDao.delete(tBaNote);//删除当前结点
            tBaNoteLast.setStatus("1");
            tBaNoteDao.update(tBaNoteLast);//把上一节点改为有效节点
        }
        tBaTaskLast.setStatus("1");
        tBaTaskDao.update(tBaTaskLast);
        result = 1;
        return result;
    }

    /**
     * 流程退回（当前处理人，把信息退回到上一处理人，把当前的处理信息删除，把上一节点的status设为1）
     */
    @Transactional(readOnly = false)
    public void sendBack(TBaTask tBaTask) {
        //获取当前节点数据
        TBaTask task = tBaTaskDao.get(tBaTask.getId());
        TBaNote note = tBaNoteDao.get(task.getNoteId());
        //先获取上一个节点的数据
        String lastTaskId = tBaTask.getLastTaskId();
        TBaTask lastTask = tBaTaskDao.get(lastTaskId);
        String lastNoteId = lastTask.getNoteId();
        TBaNote lastNote = tBaNoteDao.get(lastNoteId);
        //删除当前节点数据
        task.setId("");
        task.setActivityId("");
        tBaTaskDao.delete(task);
        tBaNoteDao.delete(note);
        //修改上一个节点的状态
        lastTask.setStatus("1");
        lastTask.setDualType("01");
        tBaTaskDao.update(lastTask);
        lastNote.setStatus("1");
        tBaNoteDao.update(lastNote);

    }

    /**
     * 下一节点人员数量检测（流程没有提交处理，未指定处理角色和人员）
     *
     * @param logo
     * @param companyId
     * @return
     */
    @Transactional(readOnly = false)
    public String checkNode(String logo, String companyId) {
        //状态0:可以发送;
        //状态1:不可以发送:流程定义不存在;
        //状态2:不可以发送:下一节点不存在;
        //状态3:不可以发送:下一节点没有人员(节点没有绑定角色);
        //状态4:不可以发送:下一节点没有人员(绑定的角色下没有人员);
        //1、检测流程定义是否存在
        String result = "0";
        TBaTempActivity tBaTempActivity = new TBaTempActivity();
        tBaTempActivity.setLogo(logo);
        Office office = new Office();
        office.setId(companyId);
        tBaTempActivity.setCompany(office);
        tBaTempActivity = tBaTempActivityService.getByTBaTempActivity(tBaTempActivity);
        if (null == tBaTempActivity) {
            result = "1"; // 状态1:不可以发送:流程定义不存在;
        }
        //2、检测下一节点是否有人员
        if (null != tBaTempActivity) {
            User userPara = new User();
            userPara.setCompany(office);
            List<TBaTempNote> tBaTempNoteList = tBaTempActivity.getTBaTempNoteList();
            TBaTempNote tBaTempNote = new TBaTempNote();
            int breakFlag = 0;
            if (null != tBaTempNoteList) {
                for (TBaTempNote tBaTempNoteTemp : tBaTempNoteList) {
                    if ("1".equals(tBaTempNoteTemp.getStatus())) {
                        tBaTempNote = tBaTempNoteTemp;
                        breakFlag += 1;
                        if (breakFlag == 2) { // 第一个运行节点是申请节点，第二个运行节点是下一个发送的节点
                            List<String> roleIdList = tBaTempNoteDao.queryNoteRoleByNoteId(tBaTempNote.getId());
                            if (null != roleIdList && roleIdList.size() > 0) {
                                int tempUser = 0;//节点对应角色下的人员数量
                                for (String roleId : roleIdList) {
                                    userPara.setRole(roleDao.get(roleId));
                                    List<User> userList = userDao.findUserByRoleId(roleId);
                                    if (null != userList && userList.size() > 0) {
                                        tempUser += userList.size();
                                    }
                                }
                                if (tempUser > 0) {
                                    result = "0";//状态0:可以发送;
                                } else {
                                    result = "4";//状态4:不可以发送:下一节点没有人员(绑定的角色下没有人员);
                                }
                            } else {
                                result = "3";//状态3:不可以发送:下一节点没有人员(节点没有绑定角色);
                            }
                            break;
                        }
                    }
                }
            }
            if (breakFlag < 2) {
                result = "2";//状态2:不可以发送:下一节点不存在;
            }
        }
        return result;
    }

    /**
     * 获得下一节点
     *
     * @return
     */
    public TBaTempNote getTBaTempNote(String id) {
        //1、根据applyId得到流程定义
        TBaActivity tBaActivity = tBaActivityDao.get(id);
        TBaTempActivity tBaTempActivity = tBaTempActivityService.get(tBaActivity.getTempActivityId());
        //2、获得当前流程定义节点
        TBaNote tBaNote = new TBaNote();
        status = "1";
        tBaNote.setStatus(status);
        tBaNote.setActivityId(tBaActivity);
        tBaNote = tBaNoteDao.getTBaNote(tBaNote);
        TBaTempNote tBaTempNote = tBaTempNoteDao.get(tBaNote.getTempNoteId());
        List<TBaTempNote> tBaTempNoteList = tBaTempActivity.getTBaTempNoteList();
        TBaTempNote tBaTempNoteNext = null;
        if (null != tBaTempNoteList) {
            for (TBaTempNote tBaTempNoteTemp : tBaTempNoteList) {
                if ("1".equals(tBaTempNoteTemp.getStatus())
                        && tBaTempNoteTemp.getPriority() > tBaTempNote.getPriority()) {
                    tBaTempNoteNext = tBaTempNoteTemp;
                    break;
                }
            }
        }
        return tBaTempNoteNext;
    }

    /**
     * 选择的岗位加载出来人员信息
     *
     * @param tempNoteId
     * @return
     */
    @Transactional(readOnly = false)
    public List<User> getUser(String tempNoteId) {
        List<User> users = new ArrayList<User>();
        User userPara = new User();
        List<String> roleIdList = tBaTempNoteDao.queryNoteRoleByNoteId(tempNoteId);
        if (null != roleIdList && roleIdList.size() > 0) {
            for (String roleId : roleIdList) {
                userPara.setRole(roleDao.get(roleId));
                List<User> userList = userDao.findUserByRoleId(roleId);
                if (null != userList && userList.size() > 0) {
                    users.addAll(userList);
                }
            }
        }
        return users;
    }

    /**
     * 流程的删除 CNAS和CMA公用
     * CNAS独立做好后，可以删除
     *
     * @param applyId
     */
    @Transactional(readOnly = false)
    public void deleteActivity(String applyId) {
        TBaActivity tBaActivity = new TBaActivity();
        tBaActivity.setApplyId(applyId);
        tBaActivity = tBaActivityDao.getByTBaActivity(tBaActivity);
        String activityId = tBaActivity.getId();
        //1、删除t_ba_task
        TBaTask tBaTask = new TBaTask();
        tBaTask.setActivityId(activityId);
        tBaTaskDao.delete(tBaTask);
        //2、删除t_ba_note
        TBaNote tBaNote = new TBaNote();
        tBaNote.setActivityId(tBaActivity);
        tBaNoteDao.delete(tBaNote);
        //3、删除t_ba_activity        
        tBaActivity.setApplyId(applyId);
        tBaActivity = tBaActivityDao.getByTBaActivity(tBaActivity);
        tBaActivityDao.delete(tBaActivity);
    }

    public TBaTask getByTBaTask(TBaTask tBaTask) {
        return (TBaTask) tBaTaskDao.getByTBaTask(tBaTask);
    }

    public TBaTask get(String taskId) {
        return (TBaTask) tBaTaskDao.get(taskId);
    }

    public TBaNote getByTBaNote(TBaNote tBaNote) {

        return tBaNoteDao.getTBaNote(tBaNote);
    }

    public List<TBaTask> getListByTask(TBaTask tBaTask) {
        return tBaTaskDao.getListByTask(tBaTask);
    }

    public TBaActivity getByTBaActivity(TBaActivity tBaActivity) {
        return (TBaActivity) tBaActivityDao.getByTBaActivity(tBaActivity);
    }

    public List<User> getUserList(String roleenname) {

        return null;
    }

    public List<User> getUserList(String roleenname, String userId) {

        return null ;
    }




    public String getNextPriority(String tempNoteId, String priority, String taskId) {
        Map<String, String> condition = new HashMap<String, String>();
        if (StringUtils.isBlank(priority)) {
            priority = "0";
        }
        String certBelong = tBaTaskDao.getCertbelong(taskId);

        String roleId = getRoleByTempNoteId(tempNoteId, certBelong);

        condition.put("priority", priority);
        condition.put("roleId", roleId);
        return tBaTaskDao.getNextPriority(condition);
    }

    /**
     * 功能描述：根据
     * 调用位置：
     * 适用数据：
     * 更新时间：2018-9-10
     * 更新人员：handf
     */
    public String getRoleByTempNoteId(String tempNoteId, String certBelong) {
        Map<String, String> condition = new HashMap<String,String>();
        condition.put("tempNoteId", tempNoteId);
        condition.put("certBelong", certBelong);
        return tBaTaskDao.getNextRole(condition);
    }

    public TBaNote getTempNoteId(String noteId) {
        return tBaNoteDao.get(noteId);
    }


    public Boolean validateHasSubmit(String noteId) {
        TBaNote note = tBaNoteDao.get(noteId);
        if ("1".equals(note.getStatus().trim())) {
            return true;
        }
        return false;

    }

    public void flowTaskToOther(String activityId, String[] userIds) {
        Map<String, String> condition = new HashMap<String, String>();
        condition.put("beforeUserId", userIds[0]);
        condition.put("afterUserId", userIds[1]);
        condition.put("activityId", activityId);
        tBaTaskDao.updateFlowReviewPerson(condition);
    }

    /**
     * 发送到下一节点所有人（确认）
     *
     * @param tBaTask
     */
    @Transactional(readOnly = false)
    public void endNode(TBaTask tBaTask) {
        //2、修改当前节点状态
        TBaNote tBaNote = tBaNoteDao.get(tBaTask.getNoteId());
        tBaNote.preUpdate();//更新tBaNote信息
        status = "2"; // 已完成
        tBaNote.setStatus(status);
        tBaNoteDao.update(tBaNote);
        tBaTask.setFormerTransactor(tBaTask.getUpdateBy().getId());
        //3、把当前用户任务记录状态为运行中的记录改为已完成
        tBaTask.setFormerTransactor(tBaTask.getUpdateBy().getId());
        tBaTask.preUpdate();
        status = "2"; // 已完成
        tBaTask.setDualType("02");
        tBaTask.setStatus(status);
        tBaTaskDao.update(tBaTask);
        //4、删除当前状态为运行中的任务记录
        tBaTaskDao.deleteOthersTask(tBaTask);
    }



    /**
     * 系统设置==》部分节点撤回 撤回保存
     *
     * @param activityId
     */
    public String retractFlowNodeSave(String activityId) {
        Map<String, String> condition = new HashMap<String, String>();
        condition.put("in_activity_id", activityId);
        tBaActivityDao.cmaRetractFlowNode(condition);
        return condition.get("out_result");
    }

    /**
     * 系统设置==》cnas部分节点撤回 撤回保存
     *
     * @param activityId
     */
    public String retractCnasFlowNodeSave(String activityId) {
        Map<String, String> condition = new HashMap<String, String>();
        condition.put("in_activity_id", activityId);
        tBaActivityDao.cnasRetractFlowNode(condition);
        return condition.get("out_result");
    }

    public void terminationTBaTask(TBaTask tBaTask) {
        //流程节点模板中查询下一节点(tempNoteId)
        //必须在对t_ba_note表数据添加前，获得下一节点，否则查询语句失效
        String tempNoteId = "";
        if (StringUtils.isEmpty(tBaTask.getNextTempNoteId())) {
            TBaTempNote tBaTempNote = getLastTBaTempNote(tBaTask.getActivityId());
            tempNoteId = tBaTempNote.getId();
        } else {
            tempNoteId = tBaTask.getNextTempNoteId();
        }
        List<User> userList = new ArrayList<User>();
        if (!tBaTask.getTempNoteId().equals(tempNoteId)) {
            //1、改变流程实例的状态，改为提交
            TBaActivity tBaActivity = tBaActivityDao.get(tBaTask.getActivityId());
            //2、修改当前节点状态
            TBaNote tBaNote = tBaNoteDao.get(tBaTask.getNoteId());
            tBaNote.preUpdate();//更新tBaNote信息
            status = "2"; // 已完成
            tBaNote.setStatus(status);
            tBaNoteDao.update(tBaNote);
            //3、把当前用户任务记录状态为运行中的记录改为已完成
            User user = UserUtils.getUser();
            //tBaTask.setFormerTransactor(tBaTask.getUpdateBy().getId());
            tBaTask.preUpdate();
            status = "2"; // 已完成
            tBaTask.setDualType("02");
            tBaTask.setStatus(status);
            tBaTaskDao.update(tBaTask);
            //4、删除当前状态为运行中的任务记录
            tBaTaskDao.deleteOthersTaskAll(tBaTask);
            //5、具体流程中添加下一个节点
            TBaTempNote tBaTempNote = tBaTempNoteDao.get(tempNoteId);
            TBaNote tBaNoteNext = new TBaNote();
            tBaNoteNext.preInsert();
            tBaNoteNext.setTempNoteId(tBaTempNote.getId());

            // 获得下一个节点和当前节点的权重
            String nextPriority = getNextPriority(tBaNote.getTempNoteId(), "0", tBaTask.getId());

            tBaNoteNext.setActivityId(tBaActivity);
            tBaNoteNext.setName(tBaTempNote.getName());
            status = "1";//运行中
            tBaNoteNext.setStatus(status);
            tBaNoteDao.insert(tBaNoteNext);
            //6、创建任务记录（创建的记录数是和人员数是一一对应的）
            User userPara = new User();
            userPara.setCompany(tBaActivity.getCompany());
            TBaTask tBaTaskNext = new TBaTask();
            tBaTaskNext.setActivityId(tBaActivity.getId());
            tBaTaskNext.setNoteId(tBaNoteNext.getId());
            tBaTaskNext.setNoteName(tBaNoteNext.getName() + "(申请被终止)");
            tBaTaskNext.setCreateName(user.getName());
            status = "2";//运行中
            tBaTaskNext.setStatus(status);

            tBaTaskNext.preInsert();
            tBaTaskNext.setUpdateBy(user);
            tBaTaskNext.setUpdateName(user.getName());
            tBaTaskNext.setLastTaskId(tBaTask.getId());
            tBaTaskNext.setDualType("01");
            tBaTaskNext.setPriority("1");
            tBaTaskDao.insert(tBaTaskNext);
        }
    }


    /**
     * 获得归档节点
     *
     * @return
     */
    public TBaTempNote getLastTBaTempNote(String id) {
        //1、根据applyId得到流程定义
        TBaActivity tBaActivity = tBaActivityDao.get(id);
        TBaTempActivity tBaTempActivity = tBaTempActivityService.get(tBaActivity.getTempActivityId());
        String tempNoteId = "9d78d324f8d24ac5893b42b22644863d";
        if ("CCSQ".equals(tBaTempActivity.getLogo())) {
            tempNoteId = "3e523bd0518c4cb0b1958af0dc5aa7d4";
        }
        TBaTempNote tBaTempNote = tBaTempNoteDao.get(tempNoteId);
        return tBaTempNote;
    }


    public String getMaxPriority(TBaTask tBaTask) {
        Map<String, String> condition = new HashMap<String, String>();

        condition.put("tempNoteId", tBaNoteDao.get(tBaTask.getNoteId()).getTempNoteId());
        return tBaTaskDao.getMaxPriority(condition);

    }
}