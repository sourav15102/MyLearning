package org.example;

public class Entry implements Gate{

    private ParkingLot parkingLot;
    Entry(ParkingLot parkingLot){
        this.parkingLot = parkingLot;

    }
    @Override
    public void perform(Occupant occupant) {
        this.parkingLot.getStrategy().allocate(occupant);
    }

    public ParkingLot getParkingLot() {
        return parkingLot;
    }
}
