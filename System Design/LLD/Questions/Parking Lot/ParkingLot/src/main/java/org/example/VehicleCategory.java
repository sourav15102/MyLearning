package org.example;

public enum VehicleCategory {
    CAR ("car"),
    BIKE ("bike"),
    TRUCK ("truck");

    private final String value;

    VehicleCategory(String value){
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }

}
