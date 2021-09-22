package com.astontech.hr.controllers;

import com.astontech.hr.domain.VO.ElementVO;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class AdminController {

    private Logger log = Logger.getLogger(AdminController.class);

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String adminHome() {
        return "admin/adminHome";
    }

    @RequestMapping(value = "/admin/element", method = RequestMethod.GET)
    public String adminElementGet(Model model) {
        model.addAttribute("elementVO", new ElementVO());
        return "admin/element";
    }

    @RequestMapping(value = "/admin/element", method = RequestMethod.POST)
    public String adminElementPost(ElementVO elementVO, Model model) {
        logElementVO(elementVO);
        return "admin/element";
    }

    private void logElementVO(ElementVO elementVO) {
        log.info("New Element Type: " + elementVO.getNewElementType());
        log.info("New Elements: " + elementVO.getNewElements());
    }
}
