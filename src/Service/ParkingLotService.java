package Service;

public interface ParkingLotService {
    void createParkingLot(String parkingLotId, int noOfFloors, int noOfSlots);
    void unParkVehicle(String ticketId);
    void display(String displayType, String vehicleType);
}
