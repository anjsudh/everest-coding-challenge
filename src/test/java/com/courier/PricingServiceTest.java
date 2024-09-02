package com.courier;

import com.courier.Delivery;
import com.courier.Package;
import com.courier.PricingService;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PricingServiceTest {

    @Test
    public void shouldCalculateTotalCostForOnePackageWithNoOffer() {
        Map<String, OfferStrategy> offers = Map.of(
            "OFR001", new WeightAndDistanceBasedOffer("OFR001", 0.10, 0, 200, 70, 200),
            "OFR002", new WeightAndDistanceBasedOffer("OFR002", 0.07, 50, 150, 100, 250),
            "OFR003", new WeightAndDistanceBasedOffer("OFR003", 0.05, 50, 250, 10, 150)
        );

        double baseCost = 100.0;
        Package package1 = new Package("PKG1", 5, 5, null);
        List<Package> packages = List.of(package1);
        double expectedCost = 175; 
        
        Delivery delivery = new Delivery(packages, baseCost);
        PricingService pricingService = new PricingService(offers);

        DeliveryPrice actualDeliveryPrice = pricingService.calculatePrice(delivery);

        DeliveryPrice expectedDeliveryPrice = new DeliveryPrice(List.of(
            new PackagePrice(packages.get(0), 175.0, 0.0)
        ));
        assertEquals(expectedDeliveryPrice, actualDeliveryPrice);
    }


    @Test
    public void shouldCalculatePriceForMultiplePackagesWithNoOffer() {
        Map<String, OfferStrategy> offers = Map.of(
            "OFR001", new WeightAndDistanceBasedOffer("OFR001", 0.10, 0, 200, 70, 200),
            "OFR002", new WeightAndDistanceBasedOffer("OFR002", 0.07, 50, 150, 100, 250),
            "OFR003", new WeightAndDistanceBasedOffer("OFR003", 0.05, 50, 250, 10, 150)
        );
        double baseCost = 100.0;
        List<Package> packages = List.of(
            new Package("PKG1", 5, 5, null),
            new Package("PKG2", 15, 5, null),
            new Package("PKG3", 10, 100, null)
        );

        Delivery delivery = new Delivery(packages, baseCost);
        PricingService pricingService = new PricingService(offers);

        DeliveryPrice actualDeliveryPrice = pricingService.calculatePrice(delivery);

        DeliveryPrice expectedDeliveryPrice = new DeliveryPrice(List.of(
            new PackagePrice(packages.get(0), 175.0, 0.0),  
            new PackagePrice(packages.get(1), 275.0, 0.0),
            new PackagePrice(packages.get(2), 700.0, 0.0) 
        ));
        assertEquals(expectedDeliveryPrice, actualDeliveryPrice);
    }

    @Test
    public void shouldApplyDiscountBasedOnWeightAndDistance() {
        Map<String, OfferStrategy> offers = Map.of(
            "OFR001", new WeightAndDistanceBasedOffer("OFR001", 0.10, 0, 200, 70, 200),
            "OFR002", new WeightAndDistanceBasedOffer("OFR002", 0.07, 50, 150, 100, 250),
            "OFR003", new WeightAndDistanceBasedOffer("OFR003", 0.05, 50, 250, 10, 150)
        );

        PricingService pricingService = new PricingService(offers);

        Package pkg = new Package("PKG1", 75, 100, "OFR001"); 
        Delivery delivery = new Delivery(List.of(pkg), 100);
        DeliveryPrice actualDeliveryPrice = pricingService.calculatePrice(delivery);

        DeliveryPrice expectedDeliveryPrice = new DeliveryPrice(List.of( new PackagePrice(pkg, 1215, 0.0) )); 
        assertEquals(expectedDeliveryPrice, actualDeliveryPrice);
    }

    @Test
    public void shouldNotApplyDiscountWhenOfferCriteriaNotMet() {
        Map<String, OfferStrategy> offers = Map.of(
            "OFR001", new WeightAndDistanceBasedOffer("OFR001", 0.10, 0, 200, 70, 200)
        );

        PricingService pricingService = new PricingService(offers);

        Package pkg = new Package("PKG1", 50, 100, "OFR001"); 
        Delivery delivery = new Delivery(List.of(pkg), 100);
        DeliveryPrice actualDeliveryPrice = pricingService.calculatePrice(delivery);

        DeliveryPrice expectedDeliveryPrice = new DeliveryPrice(List.of( new PackagePrice(pkg, 1100, 0.0) )); 
        assertEquals(expectedDeliveryPrice, actualDeliveryPrice);

    }

    @Test
    public void shouldIgnoreInvalidOfferCode() {
        Map<String, OfferStrategy> offers = Map.of(
            "OFR001", new WeightAndDistanceBasedOffer("OFR001", 0.10, 0, 200, 70, 200)
        );

        PricingService pricingService = new PricingService(offers);

        Package pkg = new Package("PKG1", 75, 100, "INVALID_CODE");
        Delivery delivery = new Delivery(List.of(pkg), 100);
        DeliveryPrice actualDeliveryPrice = pricingService.calculatePrice(delivery);

        DeliveryPrice expectedDeliveryPrice = new DeliveryPrice(List.of( new PackagePrice(pkg, 1350, 0.0) )); 
        assertEquals(expectedDeliveryPrice, actualDeliveryPrice);
    }
}