package com.base.modules.sys.utils;

import net.sf.json.JSONArray;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DbConnection
{
    public static Connection getDbConnection(){
        //dbConnection.xml文件为单独创建文件，配置连接数据库
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-context.xml");
        //dataSource配置文件中bean的ID
        DataSource dataSource = ctx.getBean("dataSource",DataSource.class);
        try {
            Connection conn = dataSource.getConnection();
            return conn;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    //合并两个JSONArray
    public static JSONArray joinJSONArray(JSONArray mData, JSONArray array) {
        for(Object jsonInfo:array){
            mData.add(jsonInfo);
        }
        return  mData;
    }


}
