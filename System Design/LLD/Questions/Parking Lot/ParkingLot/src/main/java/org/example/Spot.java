package org.example;

public class Spot {
    private String id;
    private VehicleCategory category;
    private Level level;
    private Occupant occupant;

    Spot(String id, VehicleCategory category){
        this.id = id;
        this.category = category;
        this.occupant = null;
    }

    public boolean isEmpty(){
        return occupant==null;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public VehicleCategory getCategory() {
        return category;
    }

    public void setCategory(VehicleCategory category) {
        this.category = category;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public Occupant getOccupant() {
        return occupant;
    }

    public void setOccupant(Occupant occupant) {
        this.occupant = occupant;
    }
    public void removeOccupant() {
        this.occupant = null;
    }
}
