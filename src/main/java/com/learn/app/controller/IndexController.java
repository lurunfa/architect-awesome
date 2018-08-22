package com.learn.app.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 首页控制器
 *
 * @author kevin
 * @date 2018/8/22
 * @since 0.1.0
 **/
@RequestMapping("/")
public class IndexController {
    public ModelAndView index(){
        return new ModelAndView("index");
    }
}
