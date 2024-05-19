package models;

import utils.ManufacturerNameUtility;

public abstract class WearableDevice {
    // Fields
    private String size;
    private double price;
    private String manufacturerName;
    private String material;
    private String modelName;
    private String id;

    // Constructor
    public WearableDevice(String size, double price, String manufacturerName, String material, String modelName, String id) {
        this.size = validateSize(size);
        this.price = validatePrice(price);
        this.manufacturerName = validateManufacturer(manufacturerName);
        this.material = validateMaterial(material);
        this.modelName = validateModelName(modelName);
        this.id = validateId(id);
    }

    // Getters
    public String getSize() {
        return size;
    }

    public double getPrice() {
        return price;
    }

    public String getManufacturer() {
        return manufacturerName;
    }

    public String getMaterial() {
        return material;
    }

    public String getModelName() {
        return modelName;
    }

    public String getId() {
        return id;
    }

    // Setters
    public void setSize(String size) {
        if (validateSize(size) != null) {
            this.size = size;
        }
    }

    public void setPrice(double price) {
        if (validatePrice(price) != 20) {
            this.price = price;
        }
    }

    public void setManufacturer(String manufacturerName) {
        if (validateManufacturer(manufacturerName) != null) {
            this.manufacturerName = manufacturerName;
        }
    }

    public void setMaterial(String material) {
        if (validateMaterial(material) != null) {
            this.material = material;
        }
    }

    public void setModelName(String modelName) {
        if (validateModelName(modelName) != null) {
            this.modelName = modelName;
        }
    }

    public void setId(String id) {
        if (validateId(id) != null) {
            this.id = id;
        }
    }

    // Abstract methods
    public abstract double getInsurancePremium();

    public abstract String connectToInternet();

    // Validation methods
    private String validateSize(String size) {
        return (size != null && size.length() <= 10) ? size : null;
    }

    private double validatePrice(double price) {
        return (price >= 20) ? price : 20;
    }

    private String validateManufacturer(String manufacturerName) {
        return (ManufacturerNameUtility.validateManufacturer(manufacturerName)) ? manufacturerName : "unknown";
    }

    private String validateMaterial(String material) {
        return (material != null && material.length() <= 20) ? material : null;
    }

    private String validateModelName(String modelName) {
        return (modelName != null && modelName.length() <= 30) ? modelName : null;
    }

    private String validateId(String id) {
        return (id != null && id.length() <= 10) ? id : "unknown";
    }

    @Override
    public String toString() {
        return "WearableDevice{" +
                "size='" + size + '\'' +
                ", price=" + price +
                ", manufacturerName='" + manufacturerName + '\'' +
                ", material='" + material + '\'' +
                ", modelName='" + modelName + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
