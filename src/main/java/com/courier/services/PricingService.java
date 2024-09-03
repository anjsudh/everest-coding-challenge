package com.courier.services;

import com.courier.models.Delivery;
import com.courier.models.DeliveryPrice;
import com.courier.models.Package;
import com.courier.models.PackagePrice;
import com.courier.strategies.OfferStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PricingService {
    private final Map<String, OfferStrategy> offerMap;

    public PricingService(Map<String, OfferStrategy> offerMap) {
        this.offerMap = offerMap;
    }

    public DeliveryPrice calculatePrice(Delivery delivery) {
        List<PackagePrice> packagePrices = new ArrayList<>();

        for (Package pkg : delivery.getPackages()) {
            double baseCost = delivery.getBaseCost() + (pkg.getWeight() * 10) + (pkg.getDistance() * 5);
            double discountedCost = baseCost;
            double discount = 0d;

            OfferStrategy offer = offerMap.get(pkg.getOfferCode());
            if (offer != null && offer.isApplicable(pkg)) {
                discountedCost = offer.applyDiscount(baseCost);
                discount = baseCost - discountedCost;
            }

            packagePrices.add(new PackagePrice(pkg, discountedCost, discount));
        }
        return new DeliveryPrice(packagePrices);
    }
}
