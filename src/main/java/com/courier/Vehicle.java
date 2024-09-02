package com.courier;

import com.courier.NumberUtils;
import java.util.List;

public class Vehicle {
    private final String name;
    private final double maxWeight;
    private final double speed;
    private double nextAvailableAt;

    public Vehicle(String name, double maxWeight, double speed) {
        this.name = name;
        this.maxWeight = maxWeight;
        this.speed = speed;
        this.nextAvailableAt = 0;
    }

    public String getName() {
        return name;
    }

    public double getCapacity() {
        return maxWeight;
    }

    public double getNextAvailableAt() {
        return nextAvailableAt;
    }

    public void loadPackages(List<Package> packages) {
        this.nextAvailableAt += packages.stream().mapToDouble(pkg -> NumberUtils.floorToTwoDecimalPlaces(pkg.getDistance() / speed)).max().getAsDouble() * 2;
    }

    public double getSpeed() {
        return speed;
    }

    @Override
    public String toString() {
        return String.format("Vehicle{name='%s', maxWeight=%.2f, speed=%.2f km/h}", name, maxWeight, speed);
    }
}
