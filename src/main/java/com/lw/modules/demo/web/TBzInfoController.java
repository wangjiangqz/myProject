/**
 * Copyright &copy; 2019-2019 <a href="#">版权</a> All rights reserved.
 */
package com.lw.modules.demo.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lw.common.config.Global;
import com.lw.common.persistence.Page;
import com.lw.common.web.BaseController;
import com.lw.common.utils.StringUtils;
import com.lw.modules.demo.entity.TBzInfo;
import com.lw.modules.demo.service.TBzInfoService;
import com.lw.modules.sys.entity.User;
import com.lw.modules.sys.utils.UserUtils;

/**
 * 弹出消息Controller
 * @author wj
 * @version 2017-01-24
 */
@Controller
@RequestMapping(value = "${adminPath}/demo/tBzInfo")
public class TBzInfoController extends BaseController {

	@Autowired
	private TBzInfoService tBzInfoService;
	
	@ModelAttribute
	public TBzInfo get(@RequestParam(required=false) String id) {
		TBzInfo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tBzInfoService.get(id);
		}
		if (entity == null){
			entity = new TBzInfo();
		}
		return entity;
	}
	
	@RequiresPermissions("demo:tBzInfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(TBzInfo tBzInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TBzInfo> page = tBzInfoService.findPage(new Page<TBzInfo>(request, response), tBzInfo); 
		model.addAttribute("page", page);
		return "modules/demo/tBzInfoList";
	}

	@RequiresPermissions("demo:tBzInfo:view")
	@RequestMapping(value = "form")
	public String form(TBzInfo tBzInfo, Model model) {
		model.addAttribute("tBzInfo", tBzInfo);
		return "modules/demo/tBzInfoForm";
	}

	@RequiresPermissions("demo:tBzInfo:edit")
	@RequestMapping(value = "save")
	public String save(TBzInfo tBzInfo, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tBzInfo)){
			return form(tBzInfo, model);
		}
		tBzInfoService.save(tBzInfo);
		addMessage(redirectAttributes, "保存弹出消息成功");
		return "redirect:"+Global.getAdminPath()+"/demo/tBzInfo/?repage";
	}
	
	@RequiresPermissions("demo:tBzInfo:edit")
	@RequestMapping(value = "delete")
	public String delete(TBzInfo tBzInfo, RedirectAttributes redirectAttributes) {
		tBzInfoService.delete(tBzInfo);
		addMessage(redirectAttributes, "删除弹出消息成功");
		return "redirect:"+Global.getAdminPath()+"/demo/tBzInfo/?repage";
	}
	
    @ResponseBody
    @RequestMapping(value = "showData")
    public TBzInfo showData()
    {
        User user = UserUtils.getUser();
        List<TBzInfo> info  = tBzInfoService.getByUser(user);
        if(info != null && info.size()>0)
        {
            return info.get(0);
        }
        return null;
    }
    
    @ResponseBody
    @RequestMapping(value = "haveReadData")
    public String haveReadData(String id)
    {
        tBzInfoService.haveReadData(id);return "success";
    }
}