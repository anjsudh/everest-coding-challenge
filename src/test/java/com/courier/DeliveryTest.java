package com.courier;

import org.junit.Test;
import static org.junit.Assert.*;

public class DeliveryTest {

    @Test
    public void shouldCalculateTotalCostWithNoOffer() {
        double baseCost = 100d;
        Package pkg = new Package("PKG1", 5, 5);
        Delivery delivery = new Delivery(baseCost, pkg);
        double expectedCost = 175; 
        assertEquals(expectedCost, delivery.calculateTotalCost(), 0.01);
    }
}
