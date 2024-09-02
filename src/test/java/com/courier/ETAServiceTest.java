package com.courier;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ETAServiceTest {

    @Test
    public void testEstimateForDelivery() {
        // Create vehicles with capacities and speeds
        Vehicle vehicle1 = new Vehicle("1", 200, 70);
        Vehicle vehicle2 = new Vehicle("2", 200, 70);

        List<Vehicle> vehicles = List.of(vehicle1, vehicle2);

        // Create an instance of ETAService
        ETAService etaService = new ETAService(vehicles);

        // Create packages with id, weight, distance, and offer code
        Package pkg1 = new Package("PKG1", 50, 30, "OFR001");
        Package pkg2 = new Package("PKG2", 75, 125, "OFR0008");
        Package pkg3 = new Package("PKG3", 175, 100, "OFR003");
        Package pkg4 = new Package("PKG4", 110, 60, "OFR002");
        Package pkg5 = new Package("PKG5", 155, 95, "NA");

        List<Package> packages = List.of(pkg1, pkg2, pkg3, pkg4, pkg5);

        // Create a delivery instance with packages
        Delivery delivery = new Delivery(packages, 100d);

        // Estimate delivery and calculate ETAs
        DeliveryETA deliveryETA = etaService.estimateForDelivery(delivery);
        List<PackageETA> packageETAs = deliveryETA.getPackageETAs();

        // Verify the number of package ETAs
        assertEquals(5, packageETAs.size(), "There should be 5 package ETAs");

        // Verify ETAs for each package
        assertTrue(packageETAs.stream().anyMatch(pkgETA ->
            pkgETA.getPackage().getId().equals("PKG1") && Math.abs(pkgETA.getEta() - 0.43) < 0.01));
        assertTrue(packageETAs.stream().anyMatch(pkgETA ->
            pkgETA.getPackage().getId().equals("PKG2") && Math.abs(pkgETA.getEta() - 1.79) < 0.01));
        assertTrue(packageETAs.stream().anyMatch(pkgETA ->
            pkgETA.getPackage().getId().equals("PKG3") && Math.abs(pkgETA.getEta() - 1.43) < 0.01));
        assertTrue(packageETAs.stream().anyMatch(pkgETA ->
            pkgETA.getPackage().getId().equals("PKG4") && Math.abs(pkgETA.getEta() - 0.86) < 0.01));
        assertTrue(packageETAs.stream().anyMatch(pkgETA ->
            pkgETA.getPackage().getId().equals("PKG5") && Math.abs(pkgETA.getEta() - 1.36) < 0.01));
    }
}
