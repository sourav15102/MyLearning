package org.example;
import javax.swing.border.EmptyBorder;
import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    private String id;
    private String location;
    private List<Level> levels;
    private List<Gate> entries;
    private List<Gate> exits;

    private AllocationStrategy strategy;
    private PaymentStrategy paymentStategy;



    public ParkingLot(String id, String location, List<Level> levels, int entryNum, int exitNum) {
        this.id = id;
        this.location = location;
        this.levels = levels;
        this.entries = new ArrayList<>();
        this.exits = new ArrayList<>();
        assignParkingLot();
        generateEntries(entryNum);
        generateExits(exitNum);
        strategy = new DefaultAllocationStrategy(this);
        paymentStategy = new DefaultPaymentStrategy();
    }

    private void generateExits(int exitNum) {
        for(int i=0;i<exitNum;i++){
            this.exits.add(new Exit(this));
        }
    }

    private void generateEntries(int entryNum) {
        for(int i=0;i<entryNum;i++){
            this.entries.add(new Entry(this));
        }
    }

    private void assignParkingLot() {
        this.levels.stream().forEach((level) -> level.setParkingLot(this));
    }

    public void setStrategy(AllocationStrategy strategy) {
        this.strategy = strategy;
    }

    public AllocationStrategy getStrategy() {
        return strategy;
    }

    public PaymentStrategy getPaymentStategy() {
        return paymentStategy;
    }

    public void setPaymentStategy(PaymentStrategy paymentStategy) {
        this.paymentStategy = paymentStategy;
    }

    public List<Level> getLevels() {
        return levels;
    }

    public void setLevels(List<Level> levels) {
        this.levels = levels;
    }
}
