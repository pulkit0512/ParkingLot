package Service.Impl;

import DataObjects.ParkingLot;
import DataObjects.Slots;
import DataObjects.Vehicle;
import DataObjects.VehicleType;
import Service.ParkVehicleService;

import java.util.Map;

public class ParkVehicleFirstAvailableSlot implements ParkVehicleService {
    @Override
    public void parkVehicle(String vehicleType, String regNo, String color) {
        boolean isValidVehicle = parkingLotValidator.isValidVehicleType(vehicleType);
        if (!isValidVehicle) {
            System.out.println("Not a valid vehicle Type.");
            return;
        }

        ParkingLot parkingLot = parkingLotDataStore.getParkingLot(parkingLotId);
        if (parkingLot.getVehicleRegNoSet().contains(regNo)) {
            System.out.println("Vehicle already parked.");
            return;
        }

        VehicleType typeOfVehicle = VehicleType.valueOf(vehicleType);

        Vehicle vehicle = new Vehicle(regNo, color, typeOfVehicle);
        Map<Integer, Slots> parkingLotState = parkingLot.getParkingLotState().get(typeOfVehicle);

        String ticketId = null;
        for(Map.Entry<Integer, Slots> entry : parkingLotState.entrySet()) {
            if(!entry.getValue().getFreeSlots().isEmpty()) {
                int freeSlot = entry.getValue().getFreeSlots().first();
                ticketId = parkingLotId + "_" + entry.getKey() + "_" + freeSlot;
                entry.getValue().getFreeSlots().remove(freeSlot);
                break;
            }
        }

        if (ticketId == null) {
            System.out.println("Parking Lot Full.");
        } else{
            System.out.println("Parked vehicle. Ticket ID: " + ticketId);
        }

        parkingLot.getVehicleRegNoSet().add(regNo);
        parkingLot.getVehicles().put(ticketId, vehicle);
        parkingLotDataStore.updateParkingLot(parkingLot);
    }
}
