package com.lw.modules.sys.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.apache.commons.lang3.StringEscapeUtils;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.pool.DruidDataSource;


public class DBUtil
{
    private  DruidDataSource dataSource = null;

    public DBUtil()
    {
    }

    public  synchronized void init(String driverClassName, String url, String password, String username)
    {

        if (dataSource != null)
        {
            try
            {
                dataSource.close();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            dataSource = null;
        }

        try
        {
            Properties p = new Properties();
            p.setProperty("driverClassName", driverClassName);
			p.setProperty("url",StringEscapeUtils.unescapeHtml4(url));
			p.setProperty("password", StringEscapeUtils.unescapeHtml4(password));
			p.setProperty("username",  StringEscapeUtils.unescapeHtml4(username));
			p.setProperty("maxActive", "1");
//			p.setProperty("maxIdle", "10");
			p.setProperty("maxWait", "6000");
			p.setProperty("removeAbandoned", "false");
			p.setProperty("removeAbandonedTimeout", "120");
			p.setProperty("testOnBorrow", "true");
			p.setProperty("logAbandoned", "true");
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(p);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public  synchronized Connection getConnection(String dataSourceType, String url, String username, String password) throws SQLException
    {
        if (dataSource == null)
        {
        	String driverClassName = "";
        	if("oracle".equals(dataSourceType)){
        		driverClassName = "oracle.jdbc.driver.OracleDriver";
        	}else if("mysql".equals(dataSourceType)){
        		driverClassName = "com.mysql.jdbc.Driver";
        	}else if("SqlServer".equals(dataSourceType)){
        		driverClassName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";        		                   
        	}else if("DB2".equals(dataSourceType)){
        		driverClassName = "com.ibm.db2.jcc.DB2Driver";
        	}
            init(driverClassName, StringEscapeUtils.unescapeHtml4(url), StringEscapeUtils.unescapeHtml4(password), StringEscapeUtils.unescapeHtml4(username));
        }
        Connection conn = null;
        if (dataSource != null)
        {
        	conn = dataSource.getConnection();
        }
        return conn;
    }

    public  synchronized void closeConnection(Connection conn)
    {
        if (conn != null)
        {
            try
            {
                conn.close();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args)
    {
        try
        {
        	DBUtil dbUtil = new DBUtil();
            Connection conn = dbUtil.getConnection("DB2","jdbc:db2://192.168.2.246:50001/JKSHARE","RtTY56LY^Y","jszj");
                                                                
            if (conn != null)
            {
                Statement statement = conn.createStatement();
                ResultSet rs = statement.executeQuery("select a.CODE CODE,a.NAME NAME,a.REMARK REMARK,a.STATUS STATUS from RJWCODE.CNC_SAPSR3DB_DU_FLAT_Z104 a");
                int c = rs.getMetaData().getColumnCount();
                while (rs.next())
                {
                    System.out.println();
                    for (int i = 1; i <= c; i++)
                    {
                        System.out.print(rs.getObject(i)+"\t");
                    }
                }
                rs.close();
            }
            dbUtil.closeConnection(conn);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
