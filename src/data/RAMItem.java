package data;

import java.io.Serializable;

public class RAMItem implements Serializable {

    private static final long serialVersionUID = 1L;

    public RAMItem() {
    }

    private String code;
    private String type;
    private int busSpeed;
    private String brand;
    private int quantity;
    private String productionMonthYear;
    private boolean active;

    public RAMItem(String code, String type, int busSpeed, String brand, int quantity, String productionMonthYear) {
        this.code = code;
        this.type = type;
        this.busSpeed = busSpeed;
        this.brand = brand;
        this.quantity = quantity;
        this.productionMonthYear = productionMonthYear;
        this.active = true; // Set active to true by default
    }

    public String getCode() {
        return code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getBusSpeed() {
        return busSpeed;
    }

    public void setBusSpeed(int busSpeed) {
        this.busSpeed = busSpeed;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getProductionMonthYear() {
        return productionMonthYear;
    }

    public void setProductionMonthYear(String productionMonthYear) {
        this.productionMonthYear = productionMonthYear;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isActive() {
        return active;
    }

    @Override
    public String toString() {
        return "RAMItem{" + "code=" + code + ", type=" + type + ", busSpeed=" + busSpeed + "MHz" + ", brand=" + brand + ", quantity=" + quantity + ", productionMonthYear=" + productionMonthYear + ", active=" + active + '}';
    }

}
