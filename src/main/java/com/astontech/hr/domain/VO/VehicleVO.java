package com.astontech.hr.domain.VO;

public class VehicleVO {

    private String newVehicleMake;
    private String newVehicleModel;
    private Integer newVehicleYear;
    private String newLicensePlate;
    private String newVIN;
    private String newColor;
    private boolean newIsPurchase;
    private Integer newPurchasePrice;

    public VehicleVO() {}

    //region Getters and Setters

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

    public String getNewColor() {
        return newColor;
    }

    public void setNewColor(String newColor) {
        this.newColor = newColor;
    }

    public boolean isNewIsPurchase() {
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
