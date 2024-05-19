package models;

public class SmartBand extends WearableDevice {
    private boolean heartRateMonitor;

    public SmartBand(String size, double price, String manufacturerName, String material, String modelName, String id, boolean heartRateMonitor) {
        super(size, price, manufacturerName, material, modelName, id);
        this.heartRateMonitor = heartRateMonitor;
    }

    // Getter for heartRateMonitor
    public boolean isHeartRateMonitor() {
        return heartRateMonitor;
    }

    // Setter for heartRateMonitor
    public void setHeartRateMonitor(boolean heartRateMonitor) {
        this.heartRateMonitor = heartRateMonitor;
    }

    @Override
    public double getInsurancePremium() {
        return getPrice() * 0.07;
    }

    @Override
    public String connectToInternet() {
        return "Connects to the internet via Companion App";
    }

    @Override
    public String toString() {
        String heartRateMonitorInfo = heartRateMonitor ? "Includes Heart Rate Monitor" : "No Heart Rate Monitor included";
        return super.toString() + ", " + heartRateMonitorInfo + ", Insurance Premium: " + getInsurancePremium() + ", " + connectToInternet();
    }
}