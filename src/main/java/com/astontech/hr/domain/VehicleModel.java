package com.astontech.hr.domain;

import sun.security.util.ArrayUtil;

import javax.persistence.*;
import java.util.List;

@Entity
public class VehicleModel {

    //region Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "VehicleModelId")
    private Integer Id;

    @Version
    private Integer version;

    private String vehicleModelName;

    @ManyToOne
    private VehicleMake vehicleMake;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Vehicle> vehicleList;
    //endregion

    //region Constructors
    public VehicleModel() {
    }
    public VehicleModel(String vehicleModelName, VehicleMake vehicleMake) {
        this.vehicleModelName = vehicleModelName;
        this.vehicleMake = vehicleMake;
    }
    public VehicleModel(String vehicleModelName) {
        this.vehicleModelName = vehicleModelName;
    }
    public VehicleModel(String vehicleModelName, List<Vehicle> vehicleList) {
        this.vehicleModelName = vehicleModelName;
        this.vehicleList = vehicleList;
    }
    public VehicleModel(String vehicleModelName, VehicleMake vehicleMake, List<Vehicle> vehicleList) {
        this.vehicleModelName = vehicleModelName;
        this.vehicleMake = vehicleMake;
        this.vehicleList = vehicleList;
    }
    //endregion

    //region Getters and Setters
    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getVehicleModelName() {
        return vehicleModelName;
    }

    public void setVehicleModelName(String vehicleModelName) {
        this.vehicleModelName = vehicleModelName;
    }

    public VehicleMake getVehicleMake() {
        return vehicleMake;
    }

    public void setVehicleMake(VehicleMake vehicleMake) {
        this.vehicleMake = vehicleMake;
    }

    public List<Vehicle> getVehicleList() {
        return vehicleList;
    }

    public void setVehicleList(List<Vehicle> vehicleList) {
        this.vehicleList = vehicleList;
    }
    //endregion

    //region Helpers
    public void removeChild(Integer childId) {
        for(int i = 0; i < vehicleList.size(); i++) {
            if (vehicleList.get(i).getId() == childId) {
                vehicleList.remove(i);
                i--;
            }
        }
    }
    //endregion
}