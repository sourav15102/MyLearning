package org.example;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Map;

public class Level {
    private String id;
    private ParkingLot parkingLot;
    private List<Spot> spots;
    private Map<VehicleCategory, Deque<Spot>> spotMap;

    public Level(String id, List<Spot> spots) {
        this.id = id;
        this.addSpot(spots);
    }

    public void addSpot(Spot spot) {
        spot.setLevel(this);
        spots.add(spot);
        if(!spotMap.containsKey(spot.getCategory())){
            spotMap.put(spot.getCategory(), new ArrayDeque<>());
        }
        spotMap.get(spot.getCategory()).addFirst(spot);
    }

    public void addSpot(List<Spot> spots){
        for(Spot spot: spots){
            this.addSpot(spot);
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ParkingLot getParkingLot() {
        return parkingLot;
    }

    public void setParkingLot(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public List<Spot> getSpots() {
        return spots;
    }

    public void setSpots(List<Spot> spots) {
        this.spots = spots;
    }

    public Map<VehicleCategory, Deque<Spot>> getSpotMap() {
        return spotMap;
    }

    public void setSpotMap(Map<VehicleCategory, Deque<Spot>> spotMap) {
        this.spotMap = spotMap;
    }
}
