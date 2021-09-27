package com.astontech.hr.services;

import com.astontech.hr.domain.Vehicle;

public interface VehicleService {

    Iterable<Vehicle> listAllVehicles();

    Vehicle getVehicleById(Integer id);

    Vehicle saveVehicle(Vehicle vehicle);

    Iterable<Vehicle> saveVehicleList(Iterable<Vehicle> vehicleIterable);

    void deleteVehicle(Integer id);
}
