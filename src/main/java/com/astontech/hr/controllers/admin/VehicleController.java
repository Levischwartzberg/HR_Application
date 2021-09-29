package com.astontech.hr.controllers.admin;


import com.astontech.hr.domain.*;
import com.astontech.hr.domain.VO.VehicleVO;
import com.astontech.hr.services.VehicleMakeService;
import com.astontech.hr.services.VehicleModelService;
import com.astontech.hr.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class VehicleController {

    @Autowired
    VehicleMakeService vehicleMakeService;

    @Autowired
    VehicleModelService vehicleModelService;

    @Autowired
    VehicleService vehicleService;

    //region Vehicle Section
    @RequestMapping(value = "/admin/vehicle/add", method = RequestMethod.GET)
    public String adminVehicleGet(Model model) {
        List<VehicleMake> vehicleMakeList = (List<VehicleMake>) vehicleMakeService.listAllVehicleMakes();
        List<VehicleModel> vehicleModelList = (List<VehicleModel>) vehicleModelService.listAllVehicleModels();
        model.addAttribute("vehicleVO", new VehicleVO(vehicleMakeList, vehicleModelList));
        model.addAttribute("warningAlert", "visible");
        return "admin/vehicle/vehicle_add";
    }

    @RequestMapping(value = "/admin/vehicle/add", method = RequestMethod.POST)
    public String adminVehiclePost(Model model, VehicleVO vehicleVO) {
        saveVehicleFromVehicleVO(vehicleVO);
        List<VehicleMake> vehicleMakeList = (List<VehicleMake>) vehicleMakeService.listAllVehicleMakes();
        List<VehicleModel> vehicleModelList = (List<VehicleModel>) vehicleModelService.listAllVehicleModels();
        model.addAttribute("vehicleVO", new VehicleVO(vehicleMakeList, vehicleModelList));
        model.addAttribute("successAlert", "visible");
        return "admin/vehicle/vehicle_add";
    }

    @RequestMapping(value = "/admin/vehicle/list", method = RequestMethod.GET)
    public String adminVehicleList(Model model) {
        model.addAttribute("vehicleList", vehicleService.listAllVehicles());
        return "admin/vehicle/vehicle_list";
    }

    @RequestMapping(value = "admin/vehicle/edit/{id}", method = RequestMethod.GET)
    public String vehicleEdit(@PathVariable int id, Model model) {
        Vehicle vehicle = vehicleService.getVehicleById(id);

        model.addAttribute("vehicle", vehicle);
        return "admin/vehicle/vehicle_edit";
    }

    @RequestMapping(value = "admin/vehicle/update", method = RequestMethod.POST)
    public RedirectView vehicleUpdate(Vehicle vehicle,
                                RedirectAttributes redir) {

        String vehicleId = vehicle.getId() + "";

        String vehicleMakeName1 = vehicleService.getVehicleById(vehicle.getId()).getVehicleModel().getVehicleMake().getVehicleMakeName();
        String vehicleModelName1 = vehicleService.getVehicleById(vehicle.getId()).getVehicleModel().getVehicleModelName();

        String vehicleMakeName2 = vehicle.getVehicleModel().getVehicleMake().getVehicleMakeName();
        String vehicleModelName2 = vehicle.getVehicleModel().getVehicleModelName();

        if (!(vehicleMakeName1.equals(vehicleMakeName2))) {
            VehicleMake vehicleMake = vehicleMakeService.findByVehicleMakeName(vehicleMakeName1);
            vehicleMake.setVehicleMakeName(vehicleMakeName2);
            vehicleMakeService.saveVehicleMake(vehicleMake);
        }
        if (!(vehicleModelName1.equals(vehicleModelName2))) {
            VehicleModel vehicleModel = vehicleModelService.findByVehicleModelName(vehicleModelName1);
            vehicleModel.setVehicleModelName(vehicleModelName2);
            vehicleModelService.saveVehicleModel(vehicleModel);
        }

        Vehicle updatedVehicle = vehicleService.getVehicleById(vehicle.getId());
        updatedVehicle.setVehicleYear(vehicle.getVehicleYear());
        updatedVehicle.setVIN(vehicle.getVIN());
        updatedVehicle.setColor(vehicle.getColor());
        updatedVehicle.setLicensePlate(vehicle.getLicensePlate());
        updatedVehicle.setPurchasePrice(vehicle.getPurchasePrice());
        updatedVehicle.setIsPurchase(vehicle.getIsPurchase());
        vehicleService.saveVehicle(updatedVehicle);

        RedirectView redirectView = new RedirectView("/admin/vehicle/edit/" + vehicleId, true);
        redir.addFlashAttribute("successAlert", "visible");
        return redirectView;
    }

    @RequestMapping(value = "admin/vehicle/delete/{id}", method = RequestMethod.GET)
    public String vehicleDelete(@PathVariable int id) {
        System.out.println(id);
        VehicleModel vehicleModel = vehicleModelService.findByVehicleModelName(vehicleService.getVehicleById(id).getVehicleModel().getVehicleModelName());
        Vehicle vehicle = vehicleService.getVehicleById(id);
        vehicleModel.getVehicleList().remove(vehicle);
        vehicleModelService.saveVehicleModel(vehicleModel);
        vehicleService.deleteVehicle(id);

        return "redirect:/admin/vehicle/list";
    }
    //endregion

    //region Vehicle Make Section
    @RequestMapping(value ="admin/vehiclemake/list", method = RequestMethod.GET)
    public String adminVehicleMakeList(Model model) {
        model.addAttribute("vehicleMakeList", vehicleMakeService.listAllVehicleMakes());
        return "admin/vehicle/vehicle_make_list";
    }

    @RequestMapping(value = "admin/vehiclemake/edit/{id}", method = RequestMethod.GET)
    public String vehicleMakeEdit(@PathVariable int id, Model model) {
        VehicleMake vehicleMake = vehicleMakeService.getVehicleMakeById(id);

        model.addAttribute("vehicleMake", vehicleMake);
        return "admin/vehicle/vehicle_make_edit";
    }

    @RequestMapping(value = "admin/vehiclemake/update", method = RequestMethod.POST)
    public String vehicleMakeUpdate(VehicleMake vehicleMake,
                                    Model model) {
        VehicleMake updatedVehicleMake = vehicleMakeService.getVehicleMakeById(vehicleMake.getId());
        updatedVehicleMake.setVehicleMakeName(vehicleMake.getVehicleMakeName());
        for(int i = 0; i < updatedVehicleMake.getVehicleModelList().size(); i++) {
            if (!(updatedVehicleMake.getVehicleModelList().get(i).getVehicleModelName().equals(vehicleMake.getVehicleModelList().get(i).getVehicleModelName()))) {
                updatedVehicleMake.getVehicleModelList().get(i).setVehicleModelName(vehicleMake.getVehicleModelList().get(i).getVehicleModelName());
            }
        }
        List<Integer> removeModelIndexes = new ArrayList<>();
        List<Integer> removeModelIds = new ArrayList<>();
        for(Integer i = 0; i < vehicleMake.getVehicleModelList().size(); i++) {
            if(vehicleMake.getVehicleModelList().get(i).getVehicleModelName().equals("")) {
                removeModelIndexes.add(i);
                removeModelIds.add(vehicleMake.getVehicleModelList().get(i).getId());
            }
        }
        int iter = 0;
        for (Integer index : removeModelIndexes) {
            updatedVehicleMake.getVehicleModelList().remove(updatedVehicleMake.getVehicleModelList().get(index-iter));
            iter++;
        }
        vehicleMakeService.saveVehicleMake(updatedVehicleMake);
        for(Integer id : removeModelIds) {
            vehicleModelService.deleteVehicleModel(id);
        }
        model.addAttribute("successAlert", "visible");
        return "redirect:/admin/vehiclemake/list/";
    }

    @RequestMapping(value = "admin/vehiclemake/delete/{id}", method = RequestMethod.GET)
    public String vehicleMakeDelete(@PathVariable int id) {
        vehicleMakeService.deleteVehicleMake(id);
        return "redirect:/admin/vehiclemake/list";
    }
    //endregion

    //region Helper Methods
    private void saveVehicleFromVehicleVO(VehicleVO vehicleVO) {

        VehicleMake vehicleMake = new VehicleMake();
        VehicleModel vehicleModel = new VehicleModel();
        List<Vehicle> vehicleList = new ArrayList<>();
        List<VehicleModel> vehicleModelList = new ArrayList<>();

        boolean modelExists = false;
        boolean makeExists = false;

        Vehicle vehicle = new Vehicle();
        vehicle.setColor(vehicleVO.getNewVehicleColor());
        vehicle.setLicensePlate(vehicleVO.getNewLicensePlate());
        vehicle.setVIN(vehicleVO.getNewVIN());
        vehicle.setIsPurchase(vehicleVO.getNewIsPurchase());
        vehicle.setPurchasePrice(vehicleVO.getNewPurchasePrice());
        vehicle.setVehicleYear(vehicleVO.getNewVehicleYear());

        if(!(vehicleVO.getNewVehicleMake().equals(""))) {
            makeExists = checkIfMakeExists(vehicleVO.getNewVehicleMake());
            if (makeExists) {
                vehicleMake = vehicleMakeService.findByVehicleMakeName(vehicleVO.getNewVehicleMake());
            }
            else {
                vehicleMake.setVehicleMakeName(vehicleVO.getNewVehicleMake());
            }
        }
        else {
            vehicleMake = vehicleMakeService.findByVehicleMakeName(vehicleVO.getVehicleMake());
            vehicleModelList = vehicleMake.getVehicleModelList();
        }
        if(!(vehicleVO.getNewVehicleModel().equals(""))) {
            modelExists = checkIfModelExists(vehicleVO.getNewVehicleModel());
            if (modelExists) {
                vehicleModel = vehicleModelService.findByVehicleModelName(vehicleVO.getNewVehicleModel());
                modelExists = true;
            }
            else {
                vehicleModel.setVehicleModelName(vehicleVO.getNewVehicleModel());
            }
        }
        else {
            vehicleModel = vehicleModelService.findByVehicleModelName(vehicleVO.getVehicleModel());
            vehicleList = vehicleModel.getVehicleList();
            modelExists = true;
        }

        vehicle.setVehicleModel(vehicleModel);
        vehicleModel.setVehicleMake(vehicleMake);
        vehicleList.add(vehicle);
        vehicleModel.setVehicleList(vehicleList);
        if (!modelExists) {
            vehicleModelList.add(vehicleModel);
        }
        vehicleMake.setVehicleModelList(vehicleModelList);
        vehicleMakeService.saveVehicleMake(vehicleMake);
    }

    public boolean checkIfModelExists(String vehicleModelName) {
        List<VehicleModel> vehicleModelList = (List<VehicleModel>) vehicleModelService.listAllVehicleModels();
        boolean modelExists = false;
        for (VehicleModel vehicleModel1 : vehicleModelList) {
            if (vehicleModel1.getVehicleModelName().equals(vehicleModelName)) {
                modelExists = true;
            }
        }
        return modelExists;
    }

    public boolean checkIfMakeExists(String vehicleMakeName) {
        List<VehicleMake> vehicleMakeList = (List<VehicleMake>) vehicleMakeService.listAllVehicleMakes();
        boolean makeExists = false;
        VehicleMake vehicleMake = new VehicleMake();
        for (VehicleMake vehicleMake1 : vehicleMakeList) {
            if (vehicleMake1.getVehicleMakeName().equals(vehicleMakeName)) {
                makeExists = true;
            }
        }
//        if (makeExists) {
//            vehicleMake = vehicleMakeService.findByVehicleMakeName(vehicleMakeName);
//        }
//        else {
//            vehicleMake.setVehicleMakeName(vehicleMakeName);
//        }
        return makeExists;
    }
    //endregion
}
