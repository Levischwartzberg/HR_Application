package com.astontech.hr.controllers.admin;


import com.astontech.hr.domain.VO.VehicleVO;
import com.astontech.hr.domain.VehicleMake;
import com.astontech.hr.services.VehicleMakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class VehicleController {

    @Autowired
    VehicleMakeService vehicleMakeService;

    @RequestMapping(value = "/admin/vehicle/add", method = RequestMethod.GET)
    public String adminElementGet(Model model) {
        List<VehicleMake> vehicleMakeList = (List<VehicleMake>) vehicleMakeService.listAllVehicleMakes();
        model.addAttribute("vehicleVO", new VehicleVO());
        model.addAttribute("warningAlert", "visible");
        model.addAttribute("vehicleMakeList", vehicleMakeList);
        return "admin/vehicle/vehicle_add";
    }
}
