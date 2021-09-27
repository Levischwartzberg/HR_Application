package com.astontech.hr.services.impl;

import com.astontech.hr.domain.VehicleMake;
import com.astontech.hr.domain.VehicleModel;
import com.astontech.hr.repositories.VehicleModelRepository;
import com.astontech.hr.services.VehicleModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehicleModelServiceImpl implements VehicleModelService {

    @Autowired
    VehicleModelRepository vehicleModelRepository;

    @Override
    public Iterable<VehicleModel> listAllVehicleMakes() {
        return vehicleModelRepository.findAll();
    }

    @Override
    public VehicleModel getVehicleModelById(Integer id) {
        return vehicleModelRepository.findOne(id);
    }

    @Override
    public VehicleModel saveVehicleModel(VehicleModel vehicleModel) {
        return vehicleModelRepository.save(vehicleModel);
    }

    @Override
    public Iterable<VehicleModel> saveVehicleModelList(Iterable<VehicleModel> vehicleModelIterable) {
        return vehicleModelRepository.save(vehicleModelIterable);
    }

    @Override
    public void deleteVehicleModel(Integer id) {
        vehicleModelRepository.delete(id);
    }

    @Override
    public Iterable<VehicleModel> findAllByVehicleMake(VehicleMake vehicleMake) {
        return vehicleModelRepository.findAllByVehicleMake(vehicleMake);
    }
}
