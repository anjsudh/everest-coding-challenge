package com.courier.models;

import java.util.List;
import java.util.Objects;

public class Delivery {
    private final List<Package> packages;
    private final double baseCost;

    public Delivery(List<Package> packages, double baseCost) {
        this.packages = packages;
        this.baseCost = baseCost;
    }

    public List<Package> getPackages() {
        return packages;
    }

    public double getBaseCost() {
        return baseCost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Delivery delivery = (Delivery) o;
        return Double.compare(delivery.baseCost, baseCost) == 0 &&
                packages.equals(delivery.packages);
    }

    @Override
    public int hashCode() {
        return Objects.hash(packages, baseCost);
    }

    @Override
    public String toString() {
        return String.format("Delivery{baseCost=%.1f, packages=%s}", baseCost, packages);
    }
}
