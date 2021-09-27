package com.astontech.hr.controllers.admin;


import com.astontech.hr.domain.VO.VehicleVO;
import com.astontech.hr.domain.Vehicle;
import com.astontech.hr.domain.VehicleMake;
import com.astontech.hr.domain.VehicleModel;
import com.astontech.hr.services.VehicleMakeService;
import com.astontech.hr.services.VehicleModelService;
import com.astontech.hr.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
        return "admin/vehicle/vehicle_add";
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
        vehicle.setPurchase(vehicleVO.isNewIsPurchase());
        vehicle.setPurchasePrice(vehicleVO.getNewPurchasePrice());
        vehicle.setVehicleYear(vehicleVO.getNewVehicleYear());

        if(!(vehicleVO.getNewVehicleMake().equals(""))) {
            vehicleMake.setVehicleMakeName(vehicleVO.getNewVehicleMake());
            vehicleModel.setVehicleModelName(vehicleVO.getNewVehicleModel());
        }
        else {
            vehicleMake = vehicleMakeService.findByVehicleMakeName(vehicleVO.getVehicleMake());
            vehicleModelList = vehicleMake.getVehicleModelList();

            if(!(vehicleVO.getNewVehicleModel().equals(""))) {
                vehicleModel.setVehicleModelName(vehicleVO.getNewVehicleModel());
            }
            else {
                vehicleModel = vehicleModelService.findByVehicleModelName(vehicleVO.getVehicleModel());
                vehicleList = vehicleModel.getVehicleList();
                newModel = false;
            }
        }

        vehicleList.add(vehicle);
        vehicleModel.setVehicleList(vehicleList);
        if (newModel == true) {
            vehicleModelList.add(vehicleModel);
        }
        vehicleMake.setVehicleModelList(vehicleModelList);
        vehicleMakeService.saveVehicleMake(vehicleMake);
    }
    //endregion
}
