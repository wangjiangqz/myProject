/**
 * Copyright &copy; 2019-2019 <a href="#">版权</a> All rights reserved.
 */
package com.base.modules.demo.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.base.common.web.BaseController;

/**
 * 实例Controller
 * @author nanking
 * @version 2015-07-31
 */
@Controller
@RequestMapping(value = "${adminPath}/demo/qqPopupDemo")
public class QqPopupDemoController extends BaseController {
	
	@RequestMapping(value = "form")
	public String form( Model model) {
		return "modules/demo/qqPopupDemoForm";
	}	
	
}