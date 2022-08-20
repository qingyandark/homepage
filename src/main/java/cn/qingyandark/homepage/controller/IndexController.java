package cn.qingyandark.homepage.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/", "/index"})
public class IndexController {
    public String toIndex(Model model){
        model.addAttribute("msg", "hello, shiro");
        return "index";
    }
}
