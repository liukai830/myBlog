package com.liuk.blog.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created By Liuk On 2018/05/21.
 */
@Controller
@RequestMapping(value = "error")
public class ErrorController {
    @RequestMapping(value = "error_404")
    public String error404(){
        System.out.println("asdaa");
        return "comm/error_404";
    }
}
