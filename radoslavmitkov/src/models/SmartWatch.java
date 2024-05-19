package models;
import utils.DisplayTypeUtility;

public class SmartWatch extends WearableDevice {
    private String displayType;

    public SmartWatch(String size, double price, String manufacturerName, String material, String modelName, String id, String displayType) {
        super(size, price, manufacturerName, material, modelName, id);
        this.displayType = DisplayTypeUtility.validateDisplayType(displayType) ? displayType : "LCD";
    }

    // Getter for displayType
    public String getDisplayType() {
        return displayType;
    }

    // Setter for displayType
    public void setDisplayType(String displayType) {
        if (DisplayTypeUtility.validateDisplayType(displayType)) {
            this.displayType = displayType;
        }
    }

    @Override
    public double getInsurancePremium() {
        return getPrice() * 0.06;
    }

    @Override
    public String connectToInternet() {
        return "Connects to the internet via bluetooth";
    }

    @Override
    public String toString() {
        return super.toString() + ", Display Type: " + displayType + ", Insurance Premium: " + getInsurancePremium() + ", " + connectToInternet();
    }
}
