package DataObjects;

public class Vehicle {
    private final String regNo;
    private final String color;
    private final VehicleType vehicleType;

    public Vehicle(String regNo, String color, VehicleType vehicleType) {
        this.regNo = regNo;
        this.color = color;
        this.vehicleType = vehicleType;
    }

    public String getRegNo() {
        return regNo;
    }

    public String getColor() {
        return color;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }
}
