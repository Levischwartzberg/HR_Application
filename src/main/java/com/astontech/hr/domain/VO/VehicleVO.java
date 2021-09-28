package com.astontech.hr.domain.VO;

import com.astontech.hr.domain.VehicleMake;
import com.astontech.hr.domain.VehicleModel;

import java.util.List;

public class VehicleVO {

    private List<VehicleMake> vehicleMakeList;
    private List<VehicleModel> vehicleModelList;
    private String vehicleMake;
    private String vehicleModel;
    private String newVehicleMake;
    private String newVehicleModel;
    private Integer newVehicleYear;
    private String newLicensePlate;
    private String newVIN;
    private String newVehicleColor;
    private boolean newIsPurchase;
    private Integer newPurchasePrice;

    public VehicleVO() {}
    public VehicleVO(List<VehicleMake> vehicleMakeList, List<VehicleModel> vehicleModelList) {
        this.vehicleMakeList = vehicleMakeList;
        this.vehicleModelList = vehicleModelList;
    }

    //region Getters and Setters


    public List<VehicleMake> getVehicleMakeList() {
        return vehicleMakeList;
    }

    public void setVehicleMakeList(List<VehicleMake> vehicleMakeList) {
        this.vehicleMakeList = vehicleMakeList;
    }

    public List<VehicleModel> getVehicleModelList() {
        return vehicleModelList;
    }

    public void setVehicleModelList(List<VehicleModel> vehicleModelList) {
        this.vehicleModelList = vehicleModelList;
    }

    public String getVehicleMake() {
        return vehicleMake;
    }

    public void setVehicleMake(String vehicleMake) {
        this.vehicleMake = vehicleMake;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    public String getNewVehicleMake() {
        return newVehicleMake;
    }

    public void setNewVehicleMake(String newVehicleMake) {
        this.newVehicleMake = newVehicleMake;
    }

    public String getNewVehicleModel() {
        return newVehicleModel;
    }

    public void setNewVehicleModel(String newVehicleModel) {
        this.newVehicleModel = newVehicleModel;
    }

    public Integer getNewVehicleYear() {
        return newVehicleYear;
    }

    public void setNewVehicleYear(Integer newVehicleYear) {
        this.newVehicleYear = newVehicleYear;
    }

    public String getNewLicensePlate() {
        return newLicensePlate;
    }

    public void setNewLicensePlate(String newLicensePlate) {
        this.newLicensePlate = newLicensePlate;
    }

    public String getNewVIN() {
        return newVIN;
    }

    public void setNewVIN(String newVIN) {
        this.newVIN = newVIN;
    }

    public String getNewVehicleColor() {
        return newVehicleColor;
    }

    public void setNewVehicleColor(String newVehicleColor) {
        this.newVehicleColor = newVehicleColor;
    }

    public boolean getNewIsPurchase() {
        return newIsPurchase;
    }

    public void setNewIsPurchase(boolean newIsPurchase) {
        this.newIsPurchase = newIsPurchase;
    }

    public Integer getNewPurchasePrice() {
        return newPurchasePrice;
    }

    public void setNewPurchasePrice(Integer newPurchasePrice) {
        this.newPurchasePrice = newPurchasePrice;
    }

    //endregion
}
