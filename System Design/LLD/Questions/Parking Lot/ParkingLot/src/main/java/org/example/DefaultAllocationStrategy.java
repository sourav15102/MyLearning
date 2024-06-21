package org.example;

import java.util.List;

public class DefaultAllocationStrategy implements AllocationStrategy{

    private ParkingLot parkingLot;
    DefaultAllocationStrategy(ParkingLot parkingLot){
        this.parkingLot = parkingLot;
    }
    @Override
    public boolean allocate(Occupant occupant) {
        Spot spot;
        for(Level level: this.parkingLot.getLevels()){
            if(level.getSpotMap().containsKey(occupant.getCategory()) && !level.getSpotMap().get(occupant.getCategory()).isEmpty() ){
                spot = level.getSpotMap().get(occupant.getCategory()).pollFirst();
                occupant.setSpot(spot);
                spot.setOccupant(occupant);
                return true;
            }
        }
        return false;
    }
}
