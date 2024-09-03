package com.courier.models;

import java.util.List;
import java.util.stream.Collectors;

public class Shipment {
    private final double startTime;
    private final List<Package> packages;
    private final Vehicle vehicle;

    public Shipment(double startTime, List<Package> packages, Vehicle vehicle) {
        this.startTime = startTime;
        this.packages = packages;
        this.vehicle = vehicle;
    }

    public double getStartTime() {
        return startTime;
    }

    public List<Package> getPackages() {
        return packages;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    @Override
    public String toString() {
        String packageDetails = packages.stream()
            .map(Package::getId)
            .collect(Collectors.joining(", "));

        return String.format("Shipment{startTime=%.2f, vehicle=%s, packages=[%s]}",
            startTime,
            vehicle,
            packageDetails
        );
    }
}
