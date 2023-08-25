package DataStore;

import DataObjects.ParkingLot;

public interface ParkingLotDataStore {
    void createParkingLot(ParkingLot parkingLot);
    ParkingLot getParkingLot(String parkingLot);
    void updateParkingLot(ParkingLot parkingLot);
}
