package com.astontech.hr.controllers;

import com.astontech.hr.domain.Element;
import com.astontech.hr.domain.ElementType;
import com.astontech.hr.domain.VO.ElementVO;
import com.astontech.hr.services.ElementService;
import com.astontech.hr.services.ElementTypeService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;


@Controller
public class AdminController {

    @Autowired
    private ElementTypeService elementTypeService;

    @Autowired
    private ElementService elementService;

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
        elementVO.splitNewElementsIntoArray();
        logElementVO(elementVO);
        saveElementTypeAndElementsFromVO(elementVO);
        return "admin/element";
    }

    private void saveElementTypeAndElementsFromVO(ElementVO elementVO) {
        List<Element> newElementList = new ArrayList<>();
        for(String str : elementVO.getNewElementArray()) {
            newElementList.add(new Element(str));
        }
        ElementType newElementType = new ElementType(elementVO.getNewElementType());
        newElementType.setElementList(newElementList);

        elementTypeService.saveElementType(newElementType);
    }

    private void logElementVO(ElementVO elementVO) {
        log.info("New Element Type: " + elementVO.getNewElementType());

        for (String str : elementVO.getNewElementArray()) {
            log.info("New Elements: " + str);
        }
    }
}
