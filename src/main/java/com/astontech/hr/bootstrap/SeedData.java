package com.astontech.hr.bootstrap;

import com.astontech.hr.domain.Element;
import com.astontech.hr.domain.ElementType;
import com.astontech.hr.domain.VehicleMake;
import com.astontech.hr.domain.VehicleModel;
import com.astontech.hr.services.ElementService;
import com.astontech.hr.services.ElementTypeService;
import com.astontech.hr.services.VehicleMakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SeedData implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private ElementService elementService;

    @Autowired
    private ElementTypeService elementTypeService;

    @Autowired
    private VehicleMakeService vehicleMakeService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        generateElementAndElementTypes();
        generateVehicleMakesAndModels();
    }

    private void generateElementAndElementTypes() {
        ElementType laptopType = new ElementType("Laptop");
        List<Element> laptopList = new ArrayList<>();
        laptopList.add(new Element("Acer"));
        laptopList.add(new Element("Dell"));
        laptopList.add(new Element("Samsung"));
        laptopList.add(new Element("Apple"));
        laptopList.add(new Element("Asus"));

        laptopType.setElementList(laptopList);

        elementTypeService.saveElementType(laptopType);

        ElementType phoneType = new ElementType("Phone");
        List<Element> phoneList = new ArrayList<>();
        phoneList.add(new Element("Work"));
        phoneList.add(new Element("Home"));
        phoneList.add(new Element("Cell"));

        phoneType.setElementList((phoneList));

        elementTypeService.saveElementType(phoneType);
    }

    private void generateVehicleMakesAndModels() {
        VehicleMake ford = new VehicleMake("Ford");
        VehicleMake toyota = new VehicleMake("Toyota");

        List<VehicleModel> fordModelList = new ArrayList<>();
        fordModelList.add(new VehicleModel("F-150"));
        fordModelList.add(new VehicleModel("Focus"));
        fordModelList.add(new VehicleModel("F-350"));

        List<VehicleModel> toyotaModelList = new ArrayList<>();
        toyotaModelList.add(new VehicleModel("Corolla"));
        toyotaModelList.add(new VehicleModel("Camry"));
        toyotaModelList.add(new VehicleModel("Tacoma"));

        ford.setVehicleModelList(fordModelList);
        toyota.setVehicleModelList(toyotaModelList);

        vehicleMakeService.saveVehicleMake(ford);
        vehicleMakeService.saveVehicleMake(toyota);
    }
}
