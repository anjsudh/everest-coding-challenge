package com.courier;

import java.util.HashMap;
import java.util.Map;

public class OfferFactory {

    public static Map<String, OfferStrategy> createOfferStrategies() {
        Map<String, OfferStrategy> offerStrategies = new HashMap<>();
        
        offerStrategies.put("OFR001", new WeightAndDistanceBasedOffer("OFR001", 0.10, 0, 200, 70, 200));
        offerStrategies.put("OFR002", new WeightAndDistanceBasedOffer("OFR002", 0.07, 50, 150, 100, 250));
        offerStrategies.put("OFR003", new WeightAndDistanceBasedOffer("OFR003", 0.05, 50, 250, 10, 150));

        return offerStrategies;
    }
}
