package com.courier.strategies;

import com.courier.models.Package;

public interface OfferStrategy {
    boolean isApplicable(Package pkg);
    double applyDiscount(double cost);
}

