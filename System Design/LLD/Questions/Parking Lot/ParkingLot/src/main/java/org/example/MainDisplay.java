package org.example;


import java.util.*;

public class MainDisplay implements Display{

    private ParkingLot parkingLot;
    private List<Occupant> occupants;

    MainDisplay(ParkingLot parkingLot, List<Occupant> occupants){
        this.parkingLot = parkingLot;
        this.occupants = occupants;
    }
    @Override
    public void display() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        scanner.nextLine();

        while("EXIT".equals(input)){
            System.out.println("Choose one occupants");
            for(Occupant occupant: this.occupants){
                System.out.println(occupant.getId() + ": " + occupant.getCategory());
            }

        }

    }

    public static void main(String[] args) {

        List<Spot> oneSpot = new ArrayList<>(){{
            add(new Spot("1", VehicleCategory.CAR));
            add(new Spot("2", VehicleCategory.CAR));
            add(new Spot("3", VehicleCategory.CAR));
            add(new Spot("4", VehicleCategory.BIKE));
            add(new Spot("5", VehicleCategory.BIKE));
            add(new Spot("6", VehicleCategory.TRUCK));
        }};
        List<Spot> twoSpot = new ArrayList<>(){{
            add(new Spot("7", VehicleCategory.BIKE));
            add(new Spot("8", VehicleCategory.BIKE));
            add(new Spot("9", VehicleCategory.BIKE));
            add(new Spot("10", VehicleCategory.TRUCK));
            add(new Spot("11", VehicleCategory.TRUCK));
            add(new Spot("12", VehicleCategory.CAR));
        }};
        List<Spot> threeSpot = new ArrayList<>(){{
            add(new Spot("13", VehicleCategory.TRUCK));
            add(new Spot("14", VehicleCategory.TRUCK));
            add(new Spot("15", VehicleCategory.TRUCK));
            add(new Spot("16", VehicleCategory.CAR));
            add(new Spot("17", VehicleCategory.CAR));
            add(new Spot("18", VehicleCategory.BIKE));
        }};

        List<Level> levels = new ArrayList<>(){{
            add(new Level("1", oneSpot));
            add(new Level("2", twoSpot));
            add(new Level("3", threeSpot));
        }};

        List<Occupant> occupants = new ArrayList<>(){{
            add(new Occupant("1", VehicleCategory.CAR));
            add(new Occupant("2", VehicleCategory.BIKE));
            add(new Occupant("3", VehicleCategory.TRUCK));
        }};

        ParkingLot myParkingLot = new ParkingLot("1", "Toronto", levels, 3, 3);
        Display mainDisplay = new MainDisplay(myParkingLot, occupants);
        mainDisplay.display();
    }
}
