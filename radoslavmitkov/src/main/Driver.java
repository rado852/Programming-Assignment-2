package main;

import controllers.WearableDeviceAPI;
import utils.ScannerInput;
import models.SmartBand;
import models.SmartWatch;
import models.WearableDevice;
import utils.Utilities;

import java.util.UUID;

public class Driver {


    private WearableDeviceAPI wearableAPI;

    public static void main(String[] args) throws Exception {

        new Driver().start();
    }

    public void start() {
        // Construct fields
        wearableAPI = new WearableDeviceAPI("WearableDevices.xml");

        // Load all data once the serializers are set up
        wearableAPI.load();
        runMainMenu();
    }
//TODO - construct menus
    private int mainMenu() {
        System.out.println("------WearableDevice Store Menu------");
        System.out.println("1) WearableDevice CRUD MENU");
        System.out.println("2) Reports MENU");
        System.out.println("3) Search WearableDevice Devices");
        System.out.println("4) Sort WearableDevice Devices");
        System.out.println("10) Save all");
        System.out.println("11) Load all");
        System.out.println("0) Exit");
        return ScannerInput.readNextInt("==>> ");
    }
    private void runMainMenu() {
        boolean exit = false;
        while (!exit) {
            int option = mainMenu();
            switch (option) {
                case 1 -> runWearableDeviceCrudMenu();
                case 2 -> reportsMenu();
                case 3 -> searchWearableDevices();
                case 4 -> sortWearableDevices();
                case 10 -> saveAll();
                case 11 -> loadAll();
                case 0 -> exit = true;
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
        exitApp();
    }

    private void runWearableDeviceCrudMenu() {
        boolean exit = false;
        while (!exit) {
            int option = wearableDeviceCrudMenu();
            switch (option) {
                case 1 -> runAddWearableDeviceMenu();
                case 2 -> reportsMenu();
                case 3 -> searchWearableDevices();
                case 4 -> sortWearableDevices();
                case 10 -> saveAll();
                case 11 -> loadAll();
                case 0 -> exit = true;
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
        exitApp();
    }
    private int wearableDeviceCrudMenu() {
        System.out.println("------WearableDevice CRUD Menu------");
        System.out.println("1) Add a Wearable Device");
        System.out.println("2) Delete a Wearable Device");
        System.out.println("3) List all Wearable Devices");
        System.out.println("4) Update Wearable Device");
        System.out.println("0) Return to main menu");
        return ScannerInput.readNextInt("==>> ");
//        switch (action) {
//            case 1 -> runAddWearableDeviceMenu() {
//
//            }
//        }
    }
    private int addWearableDeviceMenu() {
        System.out.println("------WearableDevice Add Menu------");
        System.out.println("1) Add a SmartBand Device");
        System.out.println("2) Add a SmartWatch Device");
        System.out.println("0) Return to CRUD Menu");
        return ScannerInput.readNextInt("==>> ");
    }

    private void runAddWearableDeviceMenu() {
        boolean exit = false;
        while (!exit) {
            int option = addWearableDeviceMenu();
            switch (option) {
                case 1 -> addWearableDevice("SmartBand");
                case 2 -> addWearableDevice("SmartWatch");
                case 0 -> exit = true;
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    /**
     * Prompt the user for all data needed to create a wearable device
     * the ID of a device is a random UUID
     * @param deviceType  The information printed to the console for the user to read
     */
    private void addWearableDevice(String deviceType) {
        //size, price, manufacturerName, material, modelName, id
        String size = ScannerInput.readNextLine("Enter size: ");
        double price = ScannerInput.readNextDouble("Enter price: ");
        String manufacturerName = ScannerInput.readNextLine("Enter manufacturer name: ");
        String material = ScannerInput.readNextLine("Enter material: ");
        String modelName = ScannerInput.readNextLine("Enter model name: ");
        String id = UUID.randomUUID().toString();

        if (deviceType.equals("SmartBand")) {
           var hasHeartRateMonitor = ScannerInput.readNextChar("Does the device have heart rate monitor? Enter Y/N");
            SmartBand device = new SmartBand(size, price, manufacturerName, material, modelName, id, Utilities.YNtoBoolean(hasHeartRateMonitor));
            wearableAPI.addWearableDevice(device);
        } else {
            String displayType = ScannerInput.readNextLine("Enter display type: ");
            SmartWatch device = new SmartWatch(size, price, manufacturerName, material, modelName, id, displayType);
            wearableAPI.addWearableDevice(device);
        }
    }
    private void addDeviceMenu(){

    }


    private void reportsMenu() {
        System.out.println("------Reports Menu------");
        System.out.println("1) WearableDevices Overview");
        System.out.println("0) Return to main menu) ");
        int choice = ScannerInput.readNextInt("==>> ");
        boolean exit = false;
        while (!exit) {
            switch (choice) {
                case 1 -> wearableAPI.listAllWearableDevices();
                case 0 -> exit = true;
            }
        }
       exitApp();
    }
    // TODO - write all the methods that are called from your menu
    private void searchByPriceRange() {
        double minPrice = ScannerInput.readNextDouble("Enter minimum price: ");
        double maxPrice = ScannerInput.readNextDouble("Enter maximum price: ");
        String devices = wearableAPI.listWearableDevicesByPriceRange(minPrice, maxPrice);
        if (!devices.isEmpty()) {
            System.out.println(devices);
        } else {
            System.out.println("No devices found in the price range " + minPrice + " to " + maxPrice);
        }
    }

    // TODO search by different criteria i.e. look at the list methods and give options based on that.
    private void searchWearableDevices() {
        System.out.println("Search Wearable Devices");
        System.out.println("1. By ID");
        System.out.println("2. By Manufacturer");
        System.out.println("3. By Price Range");
        int choice = ScannerInput.readNextInt("Enter choice: ");

        switch (choice) {
            case 1 -> searchById();
            case 2 -> searchByManufacturer();
            case 3 -> searchByPriceRange();
            default -> System.out.println("Invalid choice. Please try again.");
        }
    }

    private void searchById() {
        String id = ScannerInput.readNextLine("Enter ID: ");
        WearableDevice device = wearableAPI.getWearableDeviceById(id);
        if (device != null) {
            System.out.println(device);
        } else {
            System.out.println("No device found with ID " + id);
        }
    }

    private void searchByManufacturer() {
        String manufacturer = ScannerInput.readNextLine("Enter Manufacturer: ");
        int count = wearableAPI.numberOfWearableDeviceByChosenManufacturer(manufacturer);
        if (count > 0) {
            System.out.println("Number of devices by " + manufacturer + ": " + count);
            System.out.println(wearableAPI.listAllWearableDevicesByManufacturer(manufacturer));
        } else {
            System.out.println("No devices found for manufacturer " + manufacturer);
        }
    }

    // TODO sort  (and give a list of options - not a recurring menu thou)
    private void sortWearableDevices() {
        System.out.println("Sort Wearable Devices");
        System.out.println("1. By Price Ascending");
        System.out.println("2. By Price Descending");
        int choice = ScannerInput.readNextInt("Enter choice: ");

        switch (choice) {
            case 1 -> {
                wearableAPI.sortByPriceAscending();
                System.out.println("Devices sorted by price in ascending order.");
                System.out.println(wearableAPI.listAllWearableDevices());
            }
            case 2 -> {
                wearableAPI.sortByPriceDescending();
                System.out.println("Devices sorted by price in descending order.");
                System.out.println(wearableAPI.listAllWearableDevices());
            }
            default -> System.out.println("Invalid choice. Please try again.");
        }
    }
    //---------------------
    //  Helper Methods
    //---------------------
    private void saveAll() {
        wearableAPI.save();
        System.out.println("All data saved.");
    }

    private void loadAll() {
        wearableAPI.load();
        System.out.println("All data loaded.");
    }

    private void exitApp() {
        System.out.println("Exiting....");
        System.exit(0);
    }


    //TODO- write any helper methods that are required

    // Helper method to print a consistent header for each menu
    private void printMenuHeader(String header) {
        System.out.println("------" + header + "------");
    }

    // Helper method to print a separator line for better visual separation
    private void printSeparator() {
        System.out.println("-----------------------------------");
    }

    // Helper method to pause the execution and wait for the user to press Enter before continuing
    private void pauseAndContinue() {
        System.out.println("Press Enter to continue...");
        ScannerInput.readNextLine("");
    }


}

