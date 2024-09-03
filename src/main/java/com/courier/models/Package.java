package com.courier.models;

import java.util.Objects;

public class Package implements KnapsackItem {
    private final String id;
    private final double weight;
    private final double distance;
    private final String offerCode;

    public Package(String id, double weight, double distance, String offerCode) {
        this.id = id;
        this.weight = weight;
        this.distance = distance;
        this.offerCode = offerCode;
    }

    public String getId() {
        return id;
    }

    @Override
    public int getWeight() {
        return (int) weight;
    }

    public double getDistance() {
        return distance;
    }

    public String getOfferCode() {
        return offerCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Package pkg = (Package) o;
        return Double.compare(pkg.weight, weight) == 0 &&
                Double.compare(pkg.distance, distance) == 0 &&
                Objects.equals(id, pkg.id) &&
                Objects.equals(offerCode, pkg.offerCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, weight, distance, offerCode);
    }

    @Override
    public String toString() {
        return String.format("Package{id='%s', weight=%.1f, distance=%.1f, offerCode='%s'}", id, weight, distance, offerCode);
    }

    @Override
    public int getValue() {
        return (int) weight;
    }
}
