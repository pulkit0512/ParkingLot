package DataObjects;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ParkingLot {
    private final String parkingLotId;
    private int noOfFloors;
    private int noOfSlots;
    private Map<String, Vehicle> vehicles; // ticketId vs vehicle
    private Map<VehicleType, Map<Integer, Slots>> parkingLotState;
    private Set<String> vehicleRegNoSet;

    public ParkingLot(String parkingLotId) {
        this.parkingLotId = parkingLotId;
    }


    public String getParkingLotId() {
        return parkingLotId;
    }

    public int getNoOfFloors() {
        return noOfFloors;
    }

    public void setNoOfFloors(int noOfFloors) {
        this.noOfFloors = noOfFloors;
    }

    public int getNoOfSlots() {
        return noOfSlots;
    }

    public void setNoOfSlots(int noOfSlots) {
        this.noOfSlots = noOfSlots;
    }

    public Map<VehicleType, Map<Integer, Slots>> getParkingLotState() {
        return parkingLotState;
    }

    public void setParkingLotState(Map<VehicleType, Map<Integer, Slots>> parkingLotState) {
        this.parkingLotState = parkingLotState;
    }

    public Map<String, Vehicle> getVehicles() {
        if (vehicles == null) {
            vehicles = new HashMap<>();
        }
        return vehicles;
    }

    public Set<String> getVehicleRegNoSet() {
        if (vehicleRegNoSet == null) {
            vehicleRegNoSet = new HashSet<>();
        }
        return vehicleRegNoSet;
    }
}
