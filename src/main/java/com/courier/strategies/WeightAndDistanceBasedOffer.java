package com.courier.strategies;

import com.courier.models.Package;

public class WeightAndDistanceBasedOffer implements OfferStrategy {
    private final String code;
    private final double discountRate;
    private final double minDistance;
    private final double maxDistance;
    private final double minWeight;
    private final double maxWeight;

    public WeightAndDistanceBasedOffer(String code, double discountRate, double minDistance, double maxDistance, double minWeight, double maxWeight) {
        this.code = code;
        this.discountRate = discountRate;
        this.minDistance = minDistance;
        this.maxDistance = maxDistance;
        this.minWeight = minWeight;
        this.maxWeight = maxWeight;
    }

    @Override
    public boolean isApplicable(Package pkg) {
        return (
            pkg.getDistance() >= minDistance && 
            pkg.getDistance() <= maxDistance &&
            pkg.getWeight() >= minWeight && 
            pkg.getWeight() <= maxWeight
        );
    }

    @Override
    public double applyDiscount(double cost) {
        double discount = (cost * discountRate);
        return cost - discount;
    }

    public String getCode() {
        return code;
    }
}
