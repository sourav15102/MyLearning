package org.example;

public class Exit implements Gate{

    private ParkingLot parkingLot;

    public Exit(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;

    }

    @Override
    public void perform(Occupant occupant) {
        this.parkingLot.getPaymentStategy().acceptPay(occupant);
    }
}
