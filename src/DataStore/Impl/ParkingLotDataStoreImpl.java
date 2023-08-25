package DataStore.Impl;

import DataObjects.ParkingLot;
import DataStore.ParkingLotDataStore;

import java.util.HashMap;
import java.util.Map;

public class ParkingLotDataStoreImpl implements ParkingLotDataStore {
    private static final Map<String, ParkingLot> parkingLotDB = new HashMap<>();

    @Override
    public void createParkingLot(ParkingLot parkingLot) {
        parkingLotDB.put(parkingLot.getParkingLotId(), parkingLot);
    }

    @Override
    public ParkingLot getParkingLot(String parkingLot) {
        return parkingLotDB.get(parkingLot);
    }

    @Override
    public void updateParkingLot(ParkingLot parkingLot) {
        createParkingLot(parkingLot);
    }
}
