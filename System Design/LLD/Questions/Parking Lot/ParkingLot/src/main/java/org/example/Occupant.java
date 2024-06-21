package org.example;

public class Occupant {
    private String id;
    private Spot spot;
    private VehicleCategory category;

    public Occupant(String id, VehicleCategory category) {
        this.id = id;
        this.category = category;
        this.spot = null;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Spot getSpot() {
        return spot;
    }

    public void setSpot(Spot spot) {
        this.spot = spot;
    }
    public void removeSpot() {
        this.spot = null;
    }

    public VehicleCategory getCategory() {
        return category;
    }

    public void setCategory(VehicleCategory category) {
        this.category = category;
    }
}
