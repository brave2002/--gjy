package com.example.tmallecharts.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 周
 */
@Controller
public class IndexController {

    @RequestMapping("/")
    public String getIndex(){
        return "index.jsp";
    }


    @RequestMapping("/welcome")
    public String getWelcome(){
        return "web/welcome.jsp";
    }

}