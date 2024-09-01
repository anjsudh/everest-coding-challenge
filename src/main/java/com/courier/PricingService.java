package com.courier;

import java.util.ArrayList;
import java.util.List;

public class PricingService {
    public DeliveryPrice calculatePrice(Delivery delivery) {
        List<PackagePrice> packagePrices = new ArrayList<>();
        for (Package pkg : delivery.getPackages()) {
            double cost = delivery.getBaseCost() + (pkg.getWeight() * 10) + (pkg.getDistance() * 5);
            packagePrices.add(new PackagePrice(pkg, cost));
        }
        return new DeliveryPrice(packagePrices);
    }
}
