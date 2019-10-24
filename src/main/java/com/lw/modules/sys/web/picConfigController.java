package com.lw.modules.sys.web;


import com.lw.common.config.Global;
import com.lw.common.utils.CacheUtils;
import com.lw.common.utils.FTPUtil;
import com.lw.common.utils.FileDownUtil;
import com.lw.common.utils.StringUtils;
import com.lw.common.web.BaseController;
import com.lw.modules.base.entity.TBaFile;
import com.lw.modules.base.entity.TBaParameter;
import com.lw.modules.base.service.TBaFileService;
import com.lw.modules.base.service.TBaParameterService;
import com.lw.modules.sys.entity.Role;
import com.lw.modules.sys.utils.ParameterUtils;
import com.lw.modules.sys.utils.UserUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping(value = "${adminPath}/sys/picConfig")
public class picConfigController extends BaseController
{
    @Autowired
    private TBaFileService tBaFileService;
    public static final String CACHE_TBAPARAMETER_MAP = "tBaParameterMap";
    @Autowired
    private TBaParameterService tBaParameterService;


    @RequestMapping(value = {"list", ""})
    public String list(){
        return "modules/sys/picConfigList";
    }


    @RequestMapping(value = {"form", ""})
    public String form(HttpServletRequest request, HttpServletResponse response, Model model){
        String id=request.getParameter("id");
        model.addAttribute("id",id);
        return "modules/sys/picConfigForm";
    }

    @RequestMapping(value = {"taskShow", ""})
    public String taskShow(HttpServletRequest request, HttpServletResponse response, Model model){
        String id=request.getParameter("id");
        model.addAttribute("id",id);
        return "modules/sys/taskShow";
    }

    @RequestMapping(value = {"switchOn", ""})
    @ResponseBody
    public String switchOn(HttpServletRequest request, HttpServletResponse response, Model model,RedirectAttributes redirectAttributes){
        String id=request.getParameter("id");
        String result="0";

        TBaParameter tBaParameter= ParameterUtils.getParameter("theme","02");
        tBaParameter.setParaValue(id);
        tBaParameterService.save(tBaParameter);
        CacheUtils.remove(CACHE_TBAPARAMETER_MAP);
        String imgUrl="/static/images/lgoin20170906/";
        imgUrl.replaceAll("/", File.separator);
        String  imgPath = request.getSession().getServletContext().getRealPath(imgUrl);//上传路径

        String defineUpload = Global.getConfig("DEFINE_UPLOAD"); // true FTP服务器  false 项目服务器
        if("true".equals(defineUpload)){
            result=tBaFileService.switchOnFtp(imgPath,id);
        }else {
            String path = Global.getConfig("DEFINE_ATTACHMENT");
            result=tBaFileService.switchOnLocation(imgPath,id);
        }






        return result;
    }







    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    public String list(TBaFile tBaFile, HttpServletRequest request, HttpServletResponse response,
                       MultipartHttpServletRequest multipartHttpServletRequest) {
        // 修改的原因：1.返回map ie出现下载提示；2.返回一般字符串，ie无法接受，所以拼接成json格式
        tBaFile.preInsert();
        // result 处理结果 0 正常 1 失败：文件大于设定大小
        String name = request.getParameter("name");
        String urlReturn = ""; // 跳转路径
        try{
            MultipartFile file = multipartHttpServletRequest.getFile("file" + name);// 获得上传的附件
            long fileSize = file.getSize();
            long maxM = 100; // 文件上传大小，默认100M
            String fileId = tBaFile.getId(); // 文件ID
            String size = request.getParameter("filesize");
            if(null != size && StringUtils.isNotBlank(size)){
                maxM = Long.parseLong(size);
            }
            long maxSize = maxM * 1024 * 1024;
            if(fileSize > maxSize){
                throw new Exception();
            }
            String sizeLabel = "";
            String sizeLevel = "M";
            double tempFile = fileSize;

            if(fileSize > 1024){
                tempFile = tempFile / 1024.0;
                sizeLevel = "K";
            }
            if(tempFile > 1024){
                tempFile = tempFile / 1024;
                sizeLevel = "M";
            }

            DecimalFormat df = new DecimalFormat("######0.00");
            sizeLabel = df.format(tempFile);

            String defineUpload = Global.getConfig("DEFINE_UPLOAD"); // true FTP服务器  false 项目服务器
            String result = "";
            if("true".equals(defineUpload)){ // FTP服务器
                result = tBaFileService.saveFTPPic(tBaFile, request, multipartHttpServletRequest);
            }else { // 项目服务器
                result = tBaFileService.savePic(tBaFile, request, multipartHttpServletRequest);
            }
            urlReturn = "{\"resultU\":\"" + result + "\",\"fileId\":\"" + fileId +"\",\"filesize\":\"" + sizeLabel + sizeLevel + "\"}";

        }catch (Exception e) {
            urlReturn = "error";
        }
        return urlReturn;
    }
}
