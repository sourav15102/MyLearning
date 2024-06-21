package org.example;

public class DefaultPaymentStrategy implements PaymentStrategy{
    @Override
    public boolean acceptPay(Occupant occupant) {
        Spot spot = occupant.getSpot();
        spot.removeOccupant();
        spot.getLevel().getSpotMap().get(spot.getCategory()).addFirst(spot);
        occupant.removeSpot();
        return true;
    }
}
