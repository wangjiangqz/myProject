/**
 * Copyright &copy; 2019-2019 <a href="#">版权</a> All rights reserved.
 */
package com.base.modules.datasource.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.base.modules.base.entity.TBaField;
import com.base.modules.base.service.TBaMainSqlService;
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
import com.base.modules.datasource.entity.TBaDatasource;
import com.base.modules.datasource.service.TBaDatasourceService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 数据源Controller
 * @author yan
 * @version 2018-11-07
 */
@Controller
@RequestMapping(value = "${adminPath}/datasource/tBaDatasource")
public class TBaDatasourceController extends BaseController {

	@Autowired
	private TBaDatasourceService tBaDatasourceService;

	@Autowired
	private TBaMainSqlService tBaMainSqlService;
	
	@ModelAttribute
	public TBaDatasource get(@RequestParam(required=false) String id) {
		TBaDatasource entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tBaDatasourceService.get(id);
		}
		if (entity == null){
			entity = new TBaDatasource();
		}
		return entity;
	}
	
	@RequiresPermissions("datasource:tBaDatasource:view")
	@RequestMapping(value = {"list", ""})
	public String list(TBaDatasource tBaDatasource, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TBaDatasource> page = tBaDatasourceService.findPage(new Page<TBaDatasource>(request, response), tBaDatasource); 
		model.addAttribute("page", page);
		return "modules/datasource/tBaDatasourceList";
	}

	@RequiresPermissions("datasource:tBaDatasource:view")
	@RequestMapping(value = "form")
	public String form(TBaDatasource tBaDatasource, Model model) {
		model.addAttribute("tBaDatasource", tBaDatasource);
		return "modules/datasource/tBaDatasourceForm";
	}

	@RequiresPermissions("datasource:tBaDatasource:view")
	@RequestMapping(value = "detailsForm")
	public String detailsForm(TBaDatasource tBaDatasource, Model model) {
		model.addAttribute("tBaDatasource", tBaDatasource);
		PreparedStatement stmt = null;
		Connection con = null;//目标库
		List<TBaField> tBaFields =new ArrayList<TBaField>();
		List<TBaField> fields =new ArrayList<TBaField>();
		//这个是表名
        if("01".equals(tBaDatasource.getDataType())){
        	 //获取表名
             String[]tables=tBaDatasource.getCustomTableName().replaceAll("，",",").split(",");
             for(int i=0;i<tables.length;i++){
				 String sql="select * from "+tables[i];
				 Map<String, Object> resultMap= tBaMainSqlService.checkIsSql(sql,tables[i]);
				 if("1".equals(resultMap.get("success"))){
					tBaFields.addAll((List<TBaField>)resultMap.get("tBaFields"));
				 }
			 }
		}else if("02".equals(tBaDatasource.getDataType())){
			//获取表名
			String[]tables=tBaDatasource.getCustomTableName().replaceAll("，",",").split(",");
			//获取SQL语句
			String[]sqls=tBaDatasource.getDataSourceIdentifier().replaceAll("，",",").split(",");
			for(int i=0;i<tables.length;i++){
				Map<String, Object> resultMap= tBaMainSqlService.checkIsSql(sqls[i],tables[i]);
				if("1".equals(resultMap.get("success"))){
					tBaFields.addAll((List<TBaField>)resultMap.get("tBaFields"));
				}
			}
		}
		for(TBaField tBaField:tBaFields){
        	if(!("ID".equals(tBaField.getCodeName().toUpperCase())||"CREATE_BY".equals(tBaField.getCodeName().toUpperCase())
					||"CREATE_DATE".equals(tBaField.getCodeName().toUpperCase())||"UPDATE_BY".equals(tBaField.getCodeName().toUpperCase())
					||"UPDATE_DATE".equals(tBaField.getCodeName().toUpperCase())||"DEL_FLAG".equals(tBaField.getCodeName().toUpperCase())
					||"RELATION_ID".equals(tBaField.getCodeName().toUpperCase()))){
        		fields.add(tBaField);

			}
		}

		model.addAttribute("tBaFields", fields);

		return "modules/datasource/tBaDatasourceDetailsForm";
	}

	@RequiresPermissions("datasource:tBaDatasource:edit")
	@RequestMapping(value = "save")
	public String save(TBaDatasource tBaDatasource, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tBaDatasource)){
			return form(tBaDatasource, model);
		}
		tBaDatasourceService.save(tBaDatasource);
		addMessage(redirectAttributes, "保存数据源成功");
		return "redirect:"+Global.getAdminPath()+"/datasource/tBaDatasource/?repage";
	}
	
	@RequiresPermissions("datasource:tBaDatasource:edit")
	@RequestMapping(value = "delete")
	public String delete(TBaDatasource tBaDatasource, RedirectAttributes redirectAttributes) {
		tBaDatasourceService.delete(tBaDatasource);
		addMessage(redirectAttributes, "删除数据源成功");
		return "redirect:"+Global.getAdminPath()+"/datasource/tBaDatasource/?repage";
	}

}