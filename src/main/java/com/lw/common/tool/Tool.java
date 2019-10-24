package com.lw.common.tool;


import com.lw.common.config.Global;
import com.lw.modules.base.service.TBaFileService;
import com.lw.modules.sys.utils.ParameterUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import java.io.File;
import java.io.IOException;

public class Tool implements ApplicationListener<ContextRefreshedEvent>
{

    @Autowired
    private TBaFileService tBaFileService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent)
    {
        if(contextRefreshedEvent.getApplicationContext().getParent()==null){
            String imgUrl=File.separator+"static"+File.separator+"images"+File.separator+"lgoin20170906"+File.separator;
            /*imgUrl.replaceAll("/", File.separator);*/
            try
            {
                String  courseFile = this.getClass().getClassLoader().getResource("").getPath().replace("WEB-INF"+File.separator+"classes"+File.separator,"");
                System.out.println(courseFile);
                String  imgPath = courseFile+imgUrl;//上传路径

                String defineUpload = Global.getConfig("DEFINE_UPLOAD"); // true FTP服务器  false 项目服务器
                if("true".equals(defineUpload)){
                    tBaFileService.switchOnFtp(imgPath, ParameterUtils.getParaValue("theme","02","0"));
                }else {
                    String path = Global.getConfig("DEFINE_ATTACHMENT");
                    tBaFileService.switchOnLocation(imgPath,ParameterUtils.getParaValue("theme","02","0"));
                }
            } catch (Exception e)
            {
                e.printStackTrace();
            }

        }
    }
}
