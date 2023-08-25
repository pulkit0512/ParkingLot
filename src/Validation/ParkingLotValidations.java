package Validation;

import DataObjects.DisplayType;
import DataObjects.VehicleType;

public class ParkingLotValidations {

    public boolean isValidVehicleType(String vehicleType) {
        return vehicleType.equalsIgnoreCase(VehicleType.CAR.toString()) ||
                vehicleType.equalsIgnoreCase(VehicleType.BIKE.toString()) ||
                vehicleType.equalsIgnoreCase(VehicleType.TRUCK.toString());
    }

    public boolean isValidDisplayType(String displayType) {
        return displayType.equalsIgnoreCase(DisplayType.FREE_COUNT.toString()) ||
                displayType.equalsIgnoreCase(DisplayType.FREE_SLOTS.toString()) ||
                displayType.equalsIgnoreCase(DisplayType.OCCUPIED_SLOTS.toString());
    }
}
