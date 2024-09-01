package com.courier;

import com.courier.Delivery;
import com.courier.Package;
import com.courier.PricingService;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PricingServiceTest {

    @Test
    public void shouldCalculateTotalCostForOnePackageWithNoOffer() {
        double baseCost = 100.0;
        Package package1 = new Package("PKG1", 5, 5);
        List<Package> packages = List.of(package1);
        double expectedCost = 175; 
        
        Delivery delivery = new Delivery(packages, baseCost);
        PricingService pricingService = new PricingService();

        DeliveryPrice actualDeliveryPrice = pricingService.calculatePrice(delivery);

        DeliveryPrice expectedDeliveryPrice = new DeliveryPrice(List.of(
            new PackagePrice(packages.get(0), 175.0)
        ));
        assertEquals(actualDeliveryPrice, expectedDeliveryPrice);
    }


    @Test
    public void shouldCalculatePriceForMultiplePackagesWithNoOffer() {
        double baseCost = 100.0;
        List<Package> packages = List.of(
            new Package("PKG1", 5, 5),
            new Package("PKG2", 15, 5),
            new Package("PKG3", 10, 100)
        );

        Delivery delivery = new Delivery(packages, baseCost);
        PricingService pricingService = new PricingService();

        DeliveryPrice actualDeliveryPrice = pricingService.calculatePrice(delivery);

        DeliveryPrice expectedDeliveryPrice = new DeliveryPrice(List.of(
            new PackagePrice(packages.get(0), 175.0),  
            new PackagePrice(packages.get(1), 275.0),
            new PackagePrice(packages.get(2), 700.0) 
        ));
        assertEquals(expectedDeliveryPrice, actualDeliveryPrice);
    }
}
