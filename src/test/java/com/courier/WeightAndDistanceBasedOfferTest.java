package com.courier;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class WeightAndDistanceBasedOfferTest {

    @Test
    public void shouldApplyOfferIfWeightAndDistanceAreWithinBounds() {
        OfferStrategy offer = new WeightAndDistanceBasedOffer("OFR001", 0.10, 0, 200, 70, 200);

        Package pkg = new Package("PKG1", 100, 150, "OFR001");  // Weight and Distance are within the bounds
        assertTrue(offer.isApplicable(pkg));
        assertEquals(0.10, offer.applyDiscount(100), 0.01);
    }

    @Test
    public void shouldNotApplyOfferIfWeightIsOutsideBounds() {
        OfferStrategy offer = new WeightAndDistanceBasedOffer("OFR001", 0.10, 0, 200, 70, 200);

        Package pkg1 = new Package("PKG1", 60, 150, "OFR001");  // Weight is below the minimum bound
        assertFalse(offer.isApplicable(pkg1));

        Package pkg2 = new Package("PKG2", 220, 150, "OFR001");  // Weight is above the maximum bound
        assertFalse(offer.isApplicable(pkg2));
    }

    @Test
    public void shouldNotApplyOfferIfDistanceIsOutsideBounds() {
        OfferStrategy offer = new WeightAndDistanceBasedOffer("OFR001", 0.10, 10, 200, 70, 200);

        Package pkg1 = new Package("PKG1", 100, 5, "OFR001");  // Distance is below the minimum bound
        assertFalse(offer.isApplicable(pkg1));

        Package pkg2 = new Package("PKG2", 100, 250, "OFR001");  // Distance is above the maximum bound
        assertFalse(offer.isApplicable(pkg2));
    }

    @Test
    public void shouldNotApplyOfferIfWeightAndDistanceAreOutsideBounds() {
        OfferStrategy offer = new WeightAndDistanceBasedOffer("OFR001", 0.10, 0, 200, 70, 200);

        Package pkg = new Package("PKG1", 60, 250, "OFR001");  // Both weight and distance are outside the bounds
        assertFalse(offer.isApplicable(pkg));
    }

}
