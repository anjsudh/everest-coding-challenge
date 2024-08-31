package com.courier;

public class Delivery {
    private double baseCost = 100.0; // Base cost for delivery
    private Package pkg;

    public Delivery(double baseCost, Package pkg) {
        this.baseCost = baseCost;
        this.pkg = pkg;
    }


    /**
     * Calculates the total cost of delivery.
     * 
     * @return the total cost of delivery
     */
    public double calculateTotalCost() {
        double weightCost = pkg.getWeight() * 10; // Cost per weight unit
        double distanceCost = pkg.getDistance() * 5; // Cost per distance unit
        return baseCost + weightCost + distanceCost;
    }
}
