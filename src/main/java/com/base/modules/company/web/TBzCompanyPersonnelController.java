/**
 * Copyright &copy; 2019-2019 <a href="#">版权</a> All rights reserved.
 */
package com.base.modules.company.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.base.common.config.Global;
import com.base.common.persistence.Page;
import com.base.common.web.BaseController;
import com.base.common.utils.StringUtils;
import com.base.modules.company.entity.TBzCompanyPersonnel;
import com.base.modules.company.service.TBzCompanyPersonnelService;

/**
 * 企业人员信息Controller
 * @author xupeng
 * @version 2018-04-16
 */
@Controller
@RequestMapping(value = "${adminPath}/company/tBzCompanyPersonnel")
public class TBzCompanyPersonnelController extends BaseController {

	@Autowired
	private TBzCompanyPersonnelService tBzCompanyPersonnelService;
	
	@ModelAttribute
	public TBzCompanyPersonnel get(@RequestParam(required=false) String id) {
		TBzCompanyPersonnel entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tBzCompanyPersonnelService.get(id);
		}
		if (entity == null){
			entity = new TBzCompanyPersonnel();
		}
		return entity;
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(TBzCompanyPersonnel tBzCompanyPersonnel, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TBzCompanyPersonnel> page = tBzCompanyPersonnelService.findPage(new Page<TBzCompanyPersonnel>(request, response), tBzCompanyPersonnel); 
		model.addAttribute("page", page);
		return "modules/company/tBzCompanyPersonnelList";
	}

	@RequestMapping(value = "form")
	public String form(TBzCompanyPersonnel tBzCompanyPersonnel, Model model) {
		model.addAttribute("tBzCompanyPersonnel", tBzCompanyPersonnel);
		return "modules/company/tBzCompanyPersonnelForm";
	}

	@RequestMapping(value = "save")
	public String save(TBzCompanyPersonnel tBzCompanyPersonnel, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tBzCompanyPersonnel)){
			return form(tBzCompanyPersonnel, model);
		}
		tBzCompanyPersonnelService.save(tBzCompanyPersonnel);
		addMessage(redirectAttributes, "保存企业人员信息成功");
		return "redirect:"+Global.getAdminPath()+"/company/tBzCompanyPersonnel/?repage";
	}
	
	@RequestMapping(value = "delete")
	public String delete(TBzCompanyPersonnel tBzCompanyPersonnel, RedirectAttributes redirectAttributes) {
		tBzCompanyPersonnelService.delete(tBzCompanyPersonnel);
		addMessage(redirectAttributes, "删除企业人员信息成功");
		return "redirect:"+Global.getAdminPath()+"/company/tBzCompanyPersonnel/?repage";
	}

}