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
    public String vehicleUpdate(Vehicle vehicle,
                                    Model model) {

        String vehicleId = vehicle.getId() + "";

        Vehicle updatedVehicle = vehicleService.getVehicleById(vehicle.getId());
        updatedVehicle.setVehicleYear(vehicle.getVehicleYear());
        updatedVehicle.setVIN(vehicle.getVIN());
        updatedVehicle.setColor(vehicle.getColor());
        updatedVehicle.setLicensePlate(vehicle.getLicensePlate());
        updatedVehicle.setPurchasePrice(vehicle.getPurchasePrice());
        updatedVehicle.setIsPurchase(vehicle.getIsPurchase());
        vehicleService.saveVehicle(updatedVehicle);

//        model.addAttribute("successAlert", "visible");
        return "redirect:/admin/vehicle/edit/" + vehicleId;
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

    //region Helper Methods
    private void saveVehicleFromVehicleVO(VehicleVO vehicleVO) {

        VehicleMake vehicleMake = new VehicleMake();
        VehicleModel vehicleModel = new VehicleModel();
        List<Vehicle> vehicleList = new ArrayList<>();
        List<VehicleModel> vehicleModelList = new ArrayList<>();

        boolean newModel = true;

        Vehicle vehicle = new Vehicle();
        vehicle.setColor(vehicleVO.getNewVehicleColor());
        vehicle.setLicensePlate(vehicleVO.getNewLicensePlate());
        vehicle.setVIN(vehicleVO.getNewVIN());
        vehicle.setIsPurchase(vehicleVO.getNewIsPurchase());
        vehicle.setPurchasePrice(vehicleVO.getNewPurchasePrice());
        vehicle.setVehicleYear(vehicleVO.getNewVehicleYear());

        if(!(vehicleVO.getNewVehicleMake().equals(""))) {
            List<VehicleMake> vehicleMakeList = (List<VehicleMake>) vehicleMakeService.listAllVehicleMakes();
            boolean makeExists = false;
            for (VehicleMake vehicleMake1 : vehicleMakeList) {
                if (vehicleMake1.getVehicleMakeName().equals(vehicleVO.getNewVehicleMake())) {
                    makeExists = true;
                }
            }
            if (makeExists) {
                vehicleMake = vehicleMakeService.findByVehicleMakeName(vehicleVO.getNewVehicleMake());
            }
            else {
                vehicleMake.setVehicleMakeName(vehicleVO.getNewVehicleMake());
            }
            if(!(vehicleVO.getNewVehicleModel().equals(""))) {
                vehicleModel = checkIfModelExists(vehicleVO.getNewVehicleModel());
            }
            else {
                vehicleModel = vehicleModelService.findByVehicleModelName(vehicleVO.getVehicleModel());
                vehicleList = vehicleModel.getVehicleList();
                newModel = false;
            }
        }
        else {
            vehicleMake = vehicleMakeService.findByVehicleMakeName(vehicleVO.getVehicleMake());
            vehicleModelList = vehicleMake.getVehicleModelList();

            if(!(vehicleVO.getNewVehicleModel().equals(""))) {
                vehicleModel = checkIfModelExists(vehicleVO.getNewVehicleModel());
            }
            else {
                vehicleModel = vehicleModelService.findByVehicleModelName(vehicleVO.getVehicleModel());
                vehicleList = vehicleModel.getVehicleList();
                newModel = false;
            }
        }

        vehicle.setVehicleModel(vehicleModel);
        vehicleModel.setVehicleMake(vehicleMake);
        vehicleList.add(vehicle);
        vehicleModel.setVehicleList(vehicleList);
        if (newModel == true) {
            vehicleModelList.add(vehicleModel);
        }
        vehicleMake.setVehicleModelList(vehicleModelList);
        vehicleMakeService.saveVehicleMake(vehicleMake);
    }

    public VehicleModel checkIfModelExists(String vehicleModelName) {
        List<VehicleModel> vehicleModelList = (List<VehicleModel>) vehicleModelService.listAllVehicleModels();
        boolean modelExists = false;
        VehicleModel vehicleModel = new VehicleModel();
        for (VehicleModel vehicleModel1 : vehicleModelList) {
            if (vehicleModel1.getVehicleModelName().equals(vehicleModelName)) {
                modelExists = true;
            }
        }
        if (modelExists) {
            vehicleModel = vehicleModelService.findByVehicleModelName(vehicleModelName);
        }
        else {
            vehicleModel.setVehicleModelName(vehicleModelName);
        }
        return vehicleModel;
    }
    //endregion
}
