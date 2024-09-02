package com.courier;

public interface OfferStrategy {
    boolean isApplicable(Package pkg);
    double applyDiscount(double cost);
}

