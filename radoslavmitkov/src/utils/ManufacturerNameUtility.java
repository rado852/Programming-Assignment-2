package utils;

import java.util.ArrayList;

public class ManufacturerNameUtility {

    private static ArrayList<String> manufacturerNames = new ArrayList<>(){{
        add("APPLE");
        add("SAMSUNG");
        add("Garmin");
        add("FitBit");
        add("Whoop");
    }};


    public static boolean isValidManuName(String name) {
        //must not be case sensitive
        for (String manuName:manufacturerNames){
            if (manuName.equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }
    public static boolean validateManufacturer(String manuName) {
        return manufacturerNames.contains(manuName);
    }
}

