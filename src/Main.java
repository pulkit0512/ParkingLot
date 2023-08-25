import Service.Impl.ParkVehicleFirstAvailableSlot;
import Service.Impl.ParkingLotServiceImpl;
import Service.ParkVehicleService;
import Service.ParkingLotService;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    private static final File FILE = new File("/Users/pulkitarora/learning/ParkingLot/input.txt");
    private static final ParkingLotService parkingLotService = new ParkingLotServiceImpl();
    private static final ParkVehicleService parkVehicleService = new ParkVehicleFirstAvailableSlot();

    private static final Scanner sc;

    static {
        try {
            sc = new Scanner(FILE);
            addMockUserData();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public static void main(String[] args) {
        System.out.println("Welcome to Parking Lot Management System.");

        while(sc.hasNextLine()) {
            String input = sc.nextLine();
            System.out.println("===" + input + "===");
            if (input.equalsIgnoreCase("EXIT")) {
                break;
            }

            String[] params = input.split(" ");

            if(params[0].equalsIgnoreCase("create_parking_lot")) {
                String parkingLotId = params[1];
                int noOfFloors = Integer.parseInt(params[2]);
                int noOfSlots = Integer.parseInt(params[3]);
                parkingLotService.createParkingLot(parkingLotId, noOfFloors, noOfSlots);
            } else if (params[0].equalsIgnoreCase("park_vehicle")) {
                String vehicleType = params[1].toUpperCase();
                String regNo = params[2].toUpperCase();
                String color = params[3].toUpperCase();
                parkVehicleService.parkVehicle(vehicleType, regNo, color);
            } else if (params[0].equalsIgnoreCase("unpark_vehicle")) {
                String ticketId = params[1].toUpperCase();
                parkingLotService.unParkVehicle(ticketId);
            } else if (params[0].equalsIgnoreCase("display")) {
                String displayType = params[1].toUpperCase();
                String vehicleType = params[2].toUpperCase();
                parkingLotService.display(displayType, vehicleType);
            }
        }
    }

    private static void addMockUserData() {

    }
}