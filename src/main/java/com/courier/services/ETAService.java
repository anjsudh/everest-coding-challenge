package com.courier.services;

import com.courier.models.Delivery;
import com.courier.models.DeliveryETA;
import com.courier.models.Package;
import com.courier.models.PackageETA;
import com.courier.models.Shipment;
import com.courier.models.Vehicle;
import com.courier.utils.MathUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class ETAService {
    private final PriorityQueue<Vehicle> vehicles;

    public ETAService(List<Vehicle> vehicles) {
        this.vehicles = new PriorityQueue<Vehicle>(Comparator.comparingDouble(Vehicle::getNextAvailableAt));
        vehicles.forEach(v -> this.vehicles.add(v));
    }

    public DeliveryETA estimateForDelivery(Delivery delivery) {
        List<Package> unassignedPackages = new ArrayList<>(delivery.getPackages());
        List<PackageETA> packageETAs = new ArrayList<>();
        List<Shipment> shipments = new ArrayList<>();

        while (!unassignedPackages.isEmpty()) {
            Vehicle vehicle = getNextAvailableVehicle();
            List<Package> packages = selectPackagesForVehicle(vehicle, unassignedPackages);
            double shipmentStartTime = vehicle.getNextAvailableAt();
            vehicle.loadPackages(packages);
            shipments.add(new Shipment(shipmentStartTime, packages, vehicle));
            ;
            packages.forEach(p -> {
                double packageDeliveryTime = MathUtils.floorToTwoDecimalPlaces(p.getDistance() / vehicle.getSpeed());
                packageETAs.add(new PackageETA(p, shipmentStartTime + packageDeliveryTime));
            });
            unassignedPackages.removeAll(packages);
            this.vehicles.add(vehicle);
        }

        return new DeliveryETA(packageETAs);
    }


    private Vehicle getNextAvailableVehicle() {
        return this.vehicles.poll();
    }

    private List<Package> selectPackagesForVehicle(Vehicle vehicle, List<Package> packages) {
        List<Package> sortedPackages = new ArrayList<>(packages);
        sortedPackages.sort(Comparator.comparingDouble(Package::getWeight).reversed()
                .thenComparingDouble(Package::getDistance));

        int maxCapacity = (int) Math.floor(vehicle.getCapacity());

        double[][] dp = new double[sortedPackages.size() + 1][maxCapacity + 1];
        boolean[][] keep = new boolean[sortedPackages.size() + 1][maxCapacity + 1];

        for (int i = 1; i <= sortedPackages.size(); i++) {
            Package pkg = sortedPackages.get(i - 1);
            int weight = (int) Math.floor(pkg.getWeight());
            for (int w = 0; w <= maxCapacity; w++) {
                if (weight <= w) {
                    if (dp[i - 1][w] < dp[i - 1][w - weight] + weight) {
                        dp[i][w] = dp[i - 1][w - weight] + weight;
                        keep[i][w] = true;
                    } else {
                        dp[i][w] = dp[i - 1][w];
                    }
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        List<Package> selectedPackages = new ArrayList<>();
        int w = maxCapacity;
        for (int i = sortedPackages.size(); i > 0; i--) {
            if (keep[i][w]) {
                selectedPackages.add(sortedPackages.get(i - 1));
                w -= (int) Math.floor(sortedPackages.get(i - 1).getWeight());
            }
        }

        Collections.reverse(selectedPackages);

        return selectedPackages;
    }

}