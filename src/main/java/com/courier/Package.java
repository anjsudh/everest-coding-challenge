package com.courier;

import java.util.Objects;

public class Package {
    private final String id;
    private final double weight;
    private final double distance;

    public Package(String id, double weight, double distance) {
        this.id = id;
        this.weight = weight;
        this.distance = distance;
    }

    public String getId() { return id; }
    public double getWeight() { return weight; }
    public double getDistance() { return distance; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Package pkg = (Package) o;
        return Double.compare(pkg.weight, weight) == 0 &&
               Double.compare(pkg.distance, distance) == 0 &&
               Objects.equals(id, pkg.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, weight, distance);
    }

    @Override
    public String toString() {
        return String.format("%s %.1f %.1f", id, weight, distance);
    }
}
