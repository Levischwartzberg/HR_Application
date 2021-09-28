package com.astontech.hr.domain;

import javax.persistence.*;

@Entity
public class Vehicle {

    //region Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "VehicleId")
    private Integer Id;

    @Version
    private Integer version;

    private Integer vehicleYear;
    private String licensePlate;
    private String VIN;
    private String color;
    private boolean isPurchase;
    private Integer purchasePrice;

    @ManyToOne
    private VehicleModel vehicleModel;
    //endregion

    //region Constructors
    public Vehicle() {
    }

    public Vehicle(Integer vehicleYear, String licensePlate, String VIN, String color, boolean isPurchase, Integer purchasePrice, VehicleModel vehicleModel) {
        this.vehicleYear = vehicleYear;
        this.licensePlate = licensePlate;
        this.VIN = VIN;
        this.color = color;
        this.isPurchase = isPurchase;
        this.purchasePrice = purchasePrice;
        this.vehicleModel = vehicleModel;
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

    public Integer getVehicleYear() {
        return vehicleYear;
    }

    public void setVehicleYear(Integer vehicleYear) {
        this.vehicleYear = vehicleYear;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getVIN() {
        return VIN;
    }

    public void setVIN(String VIN) {
        this.VIN = VIN;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean getIsPurchase() {
        return isPurchase;
    }

    public void setIsPurchase(boolean purchase) {
        isPurchase = purchase;
    }

    public Integer getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(Integer purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public VehicleModel getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(VehicleModel vehicleModel) {
        this.vehicleModel = vehicleModel;
    }
    //endregion
}