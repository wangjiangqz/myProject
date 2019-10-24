package com.lw.modules.demo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 *实现功能：图片的在线裁剪
 */

@Controller
@RequestMapping(value = "${adminPath}/demo/CutImageDemo")
public class CutImageDemoController {

    @RequestMapping(value = "cutImage")
    public String cutImage() {
        return "modules/demo/cutImage";
    }
}