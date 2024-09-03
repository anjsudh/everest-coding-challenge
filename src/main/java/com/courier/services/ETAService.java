package com.courier.services;

import com.courier.models.Delivery;
import com.courier.models.DeliveryETA;
import com.courier.models.KnapsackItem;
import com.courier.models.Package;
import com.courier.models.PackageETA;
import com.courier.models.Shipment;
import com.courier.models.Vehicle;
import com.courier.utils.KnapsackSolver;
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
        List<KnapsackItem> knapsackItems = packages.stream()
                .map(pkg -> (KnapsackItem) pkg)  // Casting Package to KnapsackItem
                .collect(Collectors.toList());
        int knapsackCapacity = (int) Math.floor(vehicle.getCapacity());
        List<KnapsackItem> selectedItems = KnapsackSolver.selectItemsForKnapsack(knapsackItems, knapsackCapacity);

        return selectedItems.stream()
                .map(item -> (Package) item)
                .collect(Collectors.toList());
    }

}