package com.astontech.hr.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AdminController {

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String adminHome() {
        return "admin/adminHome";
    }

    @RequestMapping(value = "/admin/element", method = RequestMethod.GET)
    public String adminElement() {
        return "admin/element";
    }
}
