/**
 * Copyright &copy; 2019-2019 <a href="#">版权</a> All rights reserved.
 */
package com.base.modules.demo.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.base.common.web.BaseController;
import com.base.modules.demo.entity.Demo;

/**
 * 实例Controller
 * @author nanking
 * @version 2015-07-31
 */
@Controller
@RequestMapping(value = "${adminPath}/demo")
public class DemoController extends BaseController {
	@RequestMapping(value = "form")
	public String form(Demo demo, Model model) {
		return "modules/demo/demoForm";
	}	
	@RequestMapping(value = "save")
	public String save(Demo demo, Model model) {
		return "modules/demo/demoForm";
	}
}