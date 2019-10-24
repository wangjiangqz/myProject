/**
 * Copyright &copy; 2019-2019 <a href="#">版权</a> All rights reserved.
 */
package com.base.modules.code.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.base.modules.code.entity.OwnMap;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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
import com.base.modules.code.entity.TBzCode;
import com.base.modules.code.service.TBzCodeService;

import java.util.ArrayList;
import java.util.List;

/**
 * 代码Controller
 * @author wzy
 * @version 2018-08-13
 */
@Controller
@RequestMapping(value = "${adminPath}/code/tBzCode")
public class TBzCodeController extends BaseController {

	@Autowired
	private TBzCodeService tBzCodeService;
	
	@ModelAttribute
	public TBzCode get(@RequestParam(required=false) String id) {
		TBzCode entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tBzCodeService.get(id);
		}
		if (entity == null){
			entity = new TBzCode();
		}
		return entity;
	}
	
	@RequiresPermissions("code:tBzCode:view")
	@RequestMapping(value = {"list", ""})
	public String list(TBzCode tBzCode, HttpServletRequest request, HttpServletResponse response, Model model, String flag) {
		Page<OwnMap> page = null;
		int count = 0;
		StringBuffer sb = new StringBuffer();
		List<String> fields = new ArrayList<String>();
		OwnMap map = new OwnMap();
		try {
			String[] sqlArr = StringUtils.lowerCase(tBzCode.getSql()).split(",");
			//拼接sql
			for (int i=0;i<sqlArr.length;i++) {
				if (i<sqlArr.length-1) {
					sb.append(sqlArr[i]).append("  as column").append(i+1).append("  ,");
				}else {
					sb.append(sqlArr[i].split("from")[0]).append("  as column").append(i+1).append("  from  ").append(sqlArr[i].split("from")[1]);
				}
			}
			map.put("sql",sb.toString());

			page = tBzCodeService.executeSql(new Page<OwnMap>(request, response), map);
			count = sqlArr.length;//查询的字段数量
			//获取查询的字段，放入tBzCode中的selectedFields中
			for (OwnMap ownMap:page.getList()) {
				TBzCode code = new TBzCode();
				List<String> list = code.getSelectedFields();
				for (int i=0;i<count;i++) {
					if (ownMap.get("COLUMN"+(i+1)) != null) {
						list.add(i,ownMap.get("COLUMN"+(i+1)).toString());
					}else {
						list.add(i,"");
					}
				}
				code.setSelectedFields(list);
				ownMap.put("tBzCode",code);
			}


			//拼接表格第一行字段名
			for (int i=0;i<sqlArr.length;i++) {
				if (i==0) {
					fields.add(i,sqlArr[i].split("select")[1].trim());
				}else if (i==sqlArr.length-1) {
					fields.add(i,sqlArr[i].split("from")[0].trim());
				}else {
					fields.add(i,sqlArr[i].trim());
				}
			}
			model.addAttribute("fields",fields);
		}catch (Exception e) {
			e.printStackTrace();
			if (StringUtils.isNotEmpty(tBzCode.getSql())) {
				model.addAttribute("message","sql格式不正确");
				model.addAttribute("flag","false");
			}
		}
		model.addAttribute("page", page);
		model.addAttribute("tBzCode",tBzCode);
		model.addAttribute("count",count);
		return "modules/code/tBzCodeList";
	}

	@RequiresPermissions("code:tBzCode:view")
	@RequestMapping(value = "form")
	public String form(TBzCode tBzCode, Model model) {
		model.addAttribute("tBzCode", tBzCode);
		return "modules/code/tBzCodeForm";
	}

	@RequiresPermissions("code:tBzCode:edit")
	@RequestMapping(value = "save")
	public String save(TBzCode tBzCode, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tBzCode)){
			return form(tBzCode, model);
		}
		tBzCodeService.save(tBzCode);
		addMessage(redirectAttributes, "保存代码成功");
		return "redirect:"+Global.getAdminPath()+"/code/tBzCode/?repage";
	}
	
	@RequiresPermissions("code:tBzCode:edit")
	@RequestMapping(value = "delete")
	public String delete(TBzCode tBzCode, RedirectAttributes redirectAttributes) {
		tBzCodeService.delete(tBzCode);
		addMessage(redirectAttributes, "删除代码成功");
		return "redirect:"+Global.getAdminPath()+"/code/tBzCode/?repage";
	}

}