package com.courier;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            PricingService pricingService = new PricingService();

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
