package Service.Impl;

import DataObjects.*;
import DataStore.Impl.ParkingLotDataStoreImpl;
import DataStore.ParkingLotDataStore;
import Service.ParkingLotService;
import Validation.ParkingLotValidations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingLotServiceImpl implements ParkingLotService {
    private static final ParkingLotDataStore parkingLotDataStore = new ParkingLotDataStoreImpl();
    private static final ParkingLotValidations parkingLotValidator = new ParkingLotValidations();
    private static final String parkingLotId = "PR1234";
    @Override
    public void createParkingLot(String parkingLotId, int noOfFloors, int noOfSlots) {
        ParkingLot parkingLot = parkingLotDataStore.getParkingLot(parkingLotId);
        if (parkingLot != null) {
            System.out.println("Parking already created.");
            return;
        }

        parkingLot = new ParkingLot(parkingLotId);
        parkingLot.setNoOfFloors(noOfFloors);
        parkingLot.setNoOfSlots(noOfSlots);

        Map<VehicleType, Map<Integer, Slots>> parkingLotState = new HashMap<>();
        for(VehicleType vehicleType : VehicleType.values()) {
            parkingLotState.put(vehicleType, new HashMap<>());
            Map<Integer, Slots> vehicleMap = parkingLotState.get(vehicleType);
            for(int i=1;i<=noOfFloors;i++) {
                Slots slots = new Slots();
                if (vehicleType.equals(VehicleType.TRUCK)) {
                    slots.getTotalSlots().add(1);
                    slots.getFreeSlots().add(1);
                } else if (vehicleType.equals(VehicleType.BIKE)) {
                    for(int j=2;j<=Math.min(3, noOfSlots);j++) {
                        slots.getFreeSlots().add(j);
                        slots.getTotalSlots().add(j);
                    }
                } else if (vehicleType.equals(VehicleType.CAR)) {
                    for(int j=4;j<=noOfSlots;j++) {
                        slots.getFreeSlots().add(j);
                        slots.getTotalSlots().add(j);
                    }
                }
                vehicleMap.put(i, slots);
            }
        }

        parkingLot.setParkingLotState(parkingLotState);
        parkingLotDataStore.createParkingLot(parkingLot);
        System.out.println("Created parking lot with " + noOfFloors + " floors and " + noOfSlots + " slots per floor");
    }

    @Override
    public void unParkVehicle(String ticketId) {
        String[] input = ticketId.split("_");
        if (!input[0].equals(parkingLotId)) {
            System.out.println("Invalid Ticket.");
            return;
        }

        ParkingLot parkingLot = parkingLotDataStore.getParkingLot(input[0]);
        Vehicle vehicle = parkingLot.getVehicles().get(ticketId);
        if (vehicle == null) {
            System.out.println("Invalid Ticket.");
            return;
        }

        Map<Integer, Slots> vehicleMap = parkingLot.getParkingLotState().get(vehicle.getVehicleType());
        Slots slotsState = vehicleMap.get(Integer.parseInt(input[1]));
        int occupiedSlot = Integer.parseInt(input[2]);
        if (slotsState == null || slotsState.getFreeSlots().contains(occupiedSlot)) {
            System.out.println("Invalid Ticket.");
            return;
        }

        slotsState.getFreeSlots().add(occupiedSlot);
        parkingLot.getVehicles().remove(ticketId);
        parkingLot.getVehicleRegNoSet().remove(vehicle.getRegNo());
        System.out.println("Unparked vehicle with Registration Number: " + vehicle.getRegNo() + " and Color: " + vehicle.getColor());
        parkingLotDataStore.updateParkingLot(parkingLot);
    }

    @Override
    public void display(String displayType, String vehicleType) {
        boolean isValidDisplay = parkingLotValidator.isValidDisplayType(displayType);
        boolean isValidVehicle = parkingLotValidator.isValidVehicleType(vehicleType);

        if (!isValidDisplay || !isValidVehicle) {
            System.out.println("Not a valid command.");
            return;
        }

        ParkingLot parkingLot = parkingLotDataStore.getParkingLot(parkingLotId);
        VehicleType typeOfVehicle = VehicleType.valueOf(vehicleType);
        Map<Integer, Slots> vehicleMap = parkingLot.getParkingLotState().get(typeOfVehicle);

        if (displayType.equalsIgnoreCase(DisplayType.FREE_COUNT.toString())) {
            displayFreeCounts(vehicleMap, typeOfVehicle);
        } else if (displayType.equalsIgnoreCase(DisplayType.FREE_SLOTS.toString())) {
            displayFreeSlots(vehicleMap, typeOfVehicle);
        } else {
            displayOccupiedSlots(vehicleMap, typeOfVehicle);
        }
    }

    private void displayFreeCounts(Map<Integer, Slots> vehicleMap, VehicleType vehicleType) {
        for(Map.Entry<Integer, Slots> entry : vehicleMap.entrySet()) {
            System.out.println("No. of Free Slots for " + vehicleType + " on Floor " + entry.getKey() + ": "
                    + entry.getValue().getFreeSlots().size());
        }
    }

    private void displayFreeSlots(Map<Integer, Slots> vehicleMap, VehicleType vehicleType) {
        for(Map.Entry<Integer, Slots> entry : vehicleMap.entrySet()) {
            System.out.println("Free Slots for " + vehicleType + " on Floor " + entry.getKey() + ": "
                    + entry.getValue().getFreeSlots());
        }
    }

    private void displayOccupiedSlots(Map<Integer, Slots> vehicleMap, VehicleType vehicleType) {
        for(Map.Entry<Integer, Slots> entry : vehicleMap.entrySet()) {
            List<Integer> occupiedSlots = new ArrayList<>();
            for(int value : entry.getValue().getTotalSlots()) {
                if (!entry.getValue().getFreeSlots().contains(value)) {
                    occupiedSlots.add(value);
                }
            }

            System.out.println("Occupied Slots for " + vehicleType + " on Floor " + entry.getKey() + ": "
                    + occupiedSlots);
        }
    }
}
