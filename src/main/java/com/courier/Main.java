package com.courier;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.courier.models.Delivery;
import com.courier.models.Package;
import com.courier.models.Vehicle;
import com.courier.models.DeliveryETA;
import com.courier.models.DeliveryPrice;
import com.courier.services.ETAService;
import com.courier.services.PricingService;
import com.courier.factories.OfferFactory;
import com.courier.factories.VehicleFactory;
import com.courier.factories.DeliveryFactory;
import com.courier.models.PackagePrice;
import com.courier.models.PackageETA;
import com.courier.strategies.OfferStrategy;

public class Main {
    public static void main(String[] args) {
        // Initialize the services
        Map<String, OfferStrategy> offerStrategies = OfferFactory.createOfferStrategies();
        PricingService pricingService = new PricingService(offerStrategies);

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            // Create Delivery and Vehicle instances
            Delivery delivery = DeliveryFactory.createDelivery(reader);
            List<Vehicle> vehicles = VehicleFactory.createVehicles(reader);
            ETAService etaService = new ETAService(vehicles);

            // Calculate prices and ETAs
            DeliveryPrice deliveryPrice = pricingService.calculatePrice(delivery);
            DeliveryETA deliveryETA = etaService.estimateForDelivery(delivery);

            // Print results
            printDeliveryResultSummary(delivery, deliveryPrice, deliveryETA);

        } catch (IOException e) {
            System.err.println("Error reading input: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Invalid number format: " + e.getMessage());
        }
    }

    private static void printDeliveryResultSummary(Delivery delivery, DeliveryPrice deliveryPrice, DeliveryETA deliveryETA) {
        Map<Package, PackagePrice> packagePriceMap = createPackagePriceMap(deliveryPrice);
        Map<Package, PackageETA> packageETAMap = createPackageETAMap(deliveryETA);

        delivery.getPackages().forEach(pkg -> printPackageDetails(pkg, packagePriceMap, packageETAMap));
    }

    private static Map<Package, PackagePrice> createPackagePriceMap(DeliveryPrice deliveryPrice) {
        return deliveryPrice.getPackagePrices().stream()
                .collect(Collectors.toMap(PackagePrice::getPackage, pp -> pp));
    }

    private static Map<Package, PackageETA> createPackageETAMap(DeliveryETA deliveryETA) {
        return deliveryETA.getPackageETAs().stream()
                .collect(Collectors.toMap(PackageETA::getPackage, pe -> pe));
    }

    private static void printPackageDetails(Package pkg, Map<Package, PackagePrice> packagePriceMap, Map<Package, PackageETA> packageETAMap) {
        PackagePrice pkgPrice = packagePriceMap.getOrDefault(pkg, new PackagePrice(pkg, 0, 0));
        PackageETA pkgETA = packageETAMap.getOrDefault(pkg, new PackageETA(pkg, 0));

        System.out.println(pkg.getId() + " "
                + new DecimalFormat("#.##").format(pkgPrice.getDiscount()) + " "
                + new DecimalFormat("#.##").format(pkgPrice.getPrice()) + " "
                + new DecimalFormat("#.##").format(pkgETA.getEta())
        );
    }
}
