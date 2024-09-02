package com.courier;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        // Use OfferFactory to get the offer strategies
        Map<String, OfferStrategy> offerStrategies = OfferFactory.createOfferStrategies();

        PricingService pricingService = new PricingService(offerStrategies);

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            Delivery delivery = DeliveryFactory.createDelivery(reader);
            DeliveryPrice deliveryPrice = pricingService.calculatePrice(delivery);

            deliveryPrice
                .getPackagePrices()
                .forEach(pkgPrice -> 
                    System.out.println(pkgPrice)
                );

        } catch (IOException e) {
            System.err.println("Error reading input: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Invalid number format: " + e.getMessage());
        }
    }
}
