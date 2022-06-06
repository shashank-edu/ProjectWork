package com.webapp.springbootweb.Products.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
// @ResponseBody
public class IndexController {

    @RequestMapping("/")
    public String home(){
        System.out.println("main controller");
        return "home";
    }

    // @RequestMapping("/accessdenied")
	// public ModelAndView accessdenied() {
	// 	return new ModelAndView("accessdenied");
	// }

    @RequestMapping("/403")
    public String accessDenied() {
    return "Accessdenied";
}
}
