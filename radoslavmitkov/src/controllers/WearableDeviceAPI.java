package controllers;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import models.WearableDevice;
import models.SmartWatch;
import models.SmartBand;
import utils.ISerializer;

import java.util.Comparator;

/**
 * The WearableDeviceAPI class provides methods to manage a collection of wearable devices.
 * It supports CRUD operations, reporting methods, sorting methods, and persistence.
 */
public class WearableDeviceAPI implements ISerializer {

    // Create a list to store the Wearable Devices
    private List<WearableDevice> wearableList;

    // Create a File field to store filename
    private File file;

    /**
     * Constructor to initialize the filename and wearableList.
     *
     * @param filename the name of the file for persistence.
     */
    public WearableDeviceAPI(String filename) {
        this.wearableList = new ArrayList<>();
        this.file = new File(filename);
    }

    // CRUD Methods

    /**
     * Adds a new wearable device to the list.
     *
     * @param device the wearable device to add.
     * @return true if the device was added, false otherwise.
     */
    public boolean addWearableDevice(WearableDevice device) {
        return wearableList.add(device);
    }

    /**
     * Deletes a wearable device by its index.
     *
     * @param index the index of the device to delete.
     * @return the deleted wearable device, or null if the index is invalid.
     */
    public WearableDevice deleteWearableDeviceByIndex(int index) {
        if (index >= 0 && index < wearableList.size()) {
            return wearableList.remove(index);
        }
        return null;
    }

    /**
     * Deletes a wearable device by its ID.
     *
     * @param id the ID of the device to delete.
     * @return the deleted wearable device, or null if no device with the ID is found.
     */
    public WearableDevice deleteWearableDeviceById(String id) {
        for (WearableDevice device : wearableList) {
            if (device.getId().equalsIgnoreCase(id)) {
                wearableList.remove(device);
                return device;
            }
        }
        return null;
    }

    /**
     * Gets a wearable device by its index.
     *
     * @param index the index of the device to get.
     * @return the wearable device, or null if the index is invalid.
     */
    public WearableDevice getWearableDeviceByIndex(int index) {
        if (index >= 0 && index < wearableList.size()) {
            return wearableList.get(index);
        }
        return null;
    }

    /**
     * Gets a wearable device by its ID.
     *
     * @param id the ID of the device to get.
     * @return the wearable device, or null if no device with the ID is found.
     */
    public WearableDevice getWearableDeviceById(String id) {
        for (WearableDevice device : wearableList) {
            if (device.getId().equalsIgnoreCase(id)) {
                return device;
            }
        }
        return null;
    }

    // Reporting Methods Numbers

    /**
     * Gets the number of wearable devices.
     *
     * @return the number of wearable devices.
     */
    public int numberOfWearableDevices() {
        return wearableList.size();
    }

    /**
     * Gets the number of smart bands.
     *
     * @return the number of smart bands.
     */
    public int numberOfSmartBands() {
        int count = 0;
        for (WearableDevice device : wearableList) {
            if (device instanceof SmartBand) {
                count++;
            }
        }
        return count;
    }

    /**
     * Gets the number of smart watches.
     *
     * @return the number of smart watches.
     */
    public int numberOfSmartWatch() {
        int count = 0;
        for (WearableDevice device : wearableList) {
            if (device instanceof SmartWatch) {
                count++;
            }
        }
        return count;
    }

    /**
     * Gets the number of wearable devices by a chosen manufacturer.
     *
     * @param manufacturer the manufacturer name.
     * @return the number of wearable devices by the chosen manufacturer.
     */
    public int numberOfWearableDeviceByChosenManufacturer(String manufacturer) {
        int count = 0;
        for (WearableDevice device : wearableList) {
            if (device.getManufacturer().equalsIgnoreCase(manufacturer)) {
                count++;
            }
        }
        return count;
    }

    // Reporting Methods List

    /**
     * Lists all wearable devices.
     *
     * @return a string containing the details of all wearable devices.
     */
    public String listAllWearableDevices() {
        if (wearableList.isEmpty()) {
            return "No WearableDevice Devices";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < wearableList.size(); i++) {
            sb.append(i).append(": ").append(wearableList.get(i).toString()).append("\n");
        }
        return sb.toString();
    }

    /**
     * Lists all smart bands.
     *
     * @return a string containing the details of all smart bands.
     */
    public String listAllSmartBands() {
        StringBuilder sb = new StringBuilder();
        int count = 0;
        for (int i = 0; i < wearableList.size(); i++) {
            if (wearableList.get(i) instanceof SmartBand) {
                sb.append(i).append(": ").append(wearableList.get(i).toString()).append("\n");
                count++;
            }
        }
        return count == 0 ? "No Smart Bands" : sb.toString();
    }

    /**
     * Lists all smart watches.
     *
     * @return a string containing the details of all smart watches.
     */
    public String listAllSmartWatches() {
        StringBuilder sb = new StringBuilder();
        int count = 0;
        for (int i = 0; i < wearableList.size(); i++) {
            if (wearableList.get(i) instanceof SmartWatch) {
                sb.append(i).append(": ").append(wearableList.get(i).toString()).append("\n");
                count++;
            }
        }
        return count == 0 ? "No Smart Watches" : sb.toString();
    }

    /**
     * Lists all wearable devices above a certain price.
     *
     * @param price the minimum price.
     * @return a string containing the details of all wearable devices above the price.
     */
    public String listAllWearableDeviceAbovePrice(double price) {
        StringBuilder sb = new StringBuilder();
        int count = 0;
        for (WearableDevice device : wearableList) {
            if (device.getPrice() >= price) {
                sb.append(device.toString()).append("\n");
                count++;
            }
        }
        return count == 0 ? "No WearableDevice more expensive than " + price : sb.toString();
    }

    /**
     * Lists all wearable devices below a certain price.
     *
     * @param price the maximum price.
     * @return a string containing the details of all wearable devices below the price.
     */
    public String listAllWearableDeviceBelowPrice(double price) {
        StringBuilder sb = new StringBuilder();
        int count = 0;
        for (WearableDevice device : wearableList) {
            if (device.getPrice() <= price) {
                sb.append(device.toString()).append("\n");
                count++;
            }
        }
        return count == 0 ? "No WearableDevice cheaper than " + price : sb.toString();
    }

    /**
     * Lists all wearable devices within a price range.
     *
     * @param low the minimum price.
     * @param high the maximum price.
     * @return a string containing the details of all wearable devices within the price range.
     */
    public String listWearableDevicesByPriceRange(double low, double high) {
        StringBuilder result = new StringBuilder();
        for (WearableDevice device : wearableList) {
            if (device.getPrice() >= low && device.getPrice() <= high) {
                result.append(device.toString()).append("\n");
            }
        }
        return result.toString().isEmpty() ?
                "No devices found in the price range " + low + " to " + high :
                result.toString();
    }

    /**
     * Lists all wearable devices by a certain manufacturer.
     *
     * @param manufacturer the manufacturer name.
     * @return a string containing the details of all wearable devices by the manufacturer.
     */
    public String listAllWearableDevicesByManufacturer(String manufacturer) {
        StringBuilder result = new StringBuilder();
        for (WearableDevice device : wearableList) {
            if (device.getManufacturer().equalsIgnoreCase(manufacturer)) {
                result.append(device.toString()).append("\n");
            }
        }
        return result.toString().isEmpty() ?
                "No devices found for manufacturer " + manufacturer :
                result.toString();
    }

    // Update Methods

    /**
     * Updates the details of a smart watch by its ID.
     *
     * @param id the ID of the smart watch to update.
     * @param updatedDetails the new details of the smart watch.
     * @return true if the smart watch was updated, false otherwise.
     */
    public boolean updateSmartWatch(String id, SmartWatch updatedDetails) {
        for (int i = 0; i < wearableList.size(); i++) {
            WearableDevice device = wearableList.get(i);
            if (device.getId().equalsIgnoreCase(id) && device instanceof SmartWatch) {
                wearableList.set(i, updatedDetails);
                return true;
            }
        }
        return false;
    }

    /**
     * Updates the details of a smart band by its ID.
     *
     * @param id the ID of the smart band to update.
     * @param updatedDetails the new details of the smart band.
     * @return true if the smart band was updated, false otherwise.
     */
    public boolean updateSmartBand(String id, SmartBand updatedDetails) {
        for (int i = 0; i < wearableList.size(); i++) {
            WearableDevice device = wearableList.get(i);
            if (device.getId().equalsIgnoreCase(id) && device instanceof SmartBand) {
                wearableList.set(i, updatedDetails);
                return true;
            }
        }
        return false;
    }

    // Validation Method

    /**
     * Checks if an ID is valid (i.e., does not already exist in the list).
     *
     * @param id the ID to check.
     * @return true if the ID is valid, false otherwise.
     */
    public boolean isValidId(String id) {
        for (WearableDevice device : wearableList) {
            if (device.getId().equalsIgnoreCase(id)) {
                return false;
            }
        }
        return true;
    }

    // Sorting Methods

    /**
     * Sorts the wearable devices by price in descending order.
     */
    public void sortByPriceDescending() {
        wearableList.sort(Comparator.comparingDouble(WearableDevice::getPrice).reversed());
    }

    /**
     * Sorts the wearable devices by price in ascending order.
     */
    public void sortByPriceAscending() {
        wearableList.sort(Comparator.comparingDouble(WearableDevice::getPrice));
    }

    /**
     * Swaps two wearable devices in the list.
     *
     * @param list the list of wearable devices.
     * @param i the index of the first device.
     * @param j the index of the second device.
     */
    private void swapWearableDevice(List<WearableDevice> list, int i, int j) {
        WearableDevice temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }

    // Persistence Methods

    /**
     * Loads wearable devices from the file.
     */
    public void load() {
        System.out.println("Loading wearable devices");
    }

    /**
     * Saves wearable devices to the file.
     */
    public void save() {
        System.out.println("Saving wearable devices");
    }

    /**
     * Gets the filename for persistence.
     *
     * @return the filename.
     */
    public String fileName() {
        return file.getName();
    }
}
