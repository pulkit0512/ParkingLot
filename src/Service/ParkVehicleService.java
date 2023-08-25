package Service;

import DataStore.Impl.ParkingLotDataStoreImpl;
import DataStore.ParkingLotDataStore;
import Validation.ParkingLotValidations;

public interface ParkVehicleService {
    String parkingLotId = "PR1234";
    ParkingLotDataStore parkingLotDataStore = new ParkingLotDataStoreImpl();
    ParkingLotValidations parkingLotValidator = new ParkingLotValidations();
    void parkVehicle(String vehicleType, String regNo, String color);
}
