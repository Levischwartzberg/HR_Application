package com.astontech.hr.controllers.admin;


import com.astontech.hr.domain.VO.VehicleVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class VehicleController {


    @RequestMapping(value = "/admin/vehicle/add", method = RequestMethod.GET)
    public String adminElementGet(Model model) {
        model.addAttribute("vehicleVO", new VehicleVO());
        model.addAttribute("warningAlert", "visible");
        return "admin/vehicle/vehicle_add";
    }
}
