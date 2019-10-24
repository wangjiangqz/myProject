/**
 * Copyright &copy; 2019-2019 <a href="#">版权</a> All rights reserved.
 */
package com.lw.modules.workflow.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lw.common.config.Global;
import com.lw.common.persistence.Page;
import com.lw.common.web.BaseController;
import com.lw.common.utils.StringUtils;
import com.lw.modules.sys.service.SystemService;
import com.lw.modules.workflow.entity.TBzRolePriority;
import com.lw.modules.workflow.service.TBzRolePriorityService;

/**
 * 角色定义权重管理Controller
 * @author handf
 * @version 2016-06-25
 */
@Controller
@RequestMapping(value = "${adminPath}/workflow/tBzRolePriority")
public class TBzRolePriorityController extends BaseController {

	@Autowired
	private TBzRolePriorityService tBzRolePriorityService;
	
	@Autowired
	private SystemService systemService;
	
	@ModelAttribute
	public TBzRolePriority get(@RequestParam(required=false) String id) {
		TBzRolePriority entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tBzRolePriorityService.get(id);
		}
		if (entity == null){
			entity = new TBzRolePriority();
		}
		return entity;
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(TBzRolePriority tBzRolePriority, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TBzRolePriority> page = tBzRolePriorityService.findPage(new Page<TBzRolePriority>(request, response), tBzRolePriority); 
		model.addAttribute("page", page);
		model.addAttribute("allRoles", systemService.findAllRole());
		return "modules/workflow/tBzRolePriorityList";
	}

	@RequestMapping(value = "form")
	public String form(TBzRolePriority tBzRolePriority, Model model) {
		
		model.addAttribute("allRoles", systemService.findAllRole());
		model.addAttribute("tBzRolePriority", tBzRolePriority);
		return "modules/workflow/tBzRolePriorityForm";
	}

	@RequestMapping(value = "save")
	public String save(TBzRolePriority tBzRolePriority, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tBzRolePriority)){
			return form(tBzRolePriority, model);
		}
		tBzRolePriorityService.save(tBzRolePriority);
		addMessage(redirectAttributes, "保存角色定义权重管理成功");
		return "redirect:"+Global.getAdminPath()+"/workflow/tBzRolePriority/?repage";
	}
	
	@RequestMapping(value = "delete")
	public String delete(TBzRolePriority tBzRolePriority, RedirectAttributes redirectAttributes) {
		tBzRolePriorityService.delete(tBzRolePriority);
		addMessage(redirectAttributes, "删除角色定义权重管理成功");
		return "redirect:"+Global.getAdminPath()+"/workflow/tBzRolePriority/?repage";
	}

}