package com.courier.models;

import java.util.List;
import java.util.Objects;

public class DeliveryPrice {
    private final List<PackagePrice> packagePrices;

    public DeliveryPrice(List<PackagePrice> packagePrices) {
        this.packagePrices = packagePrices;
    }

    public List<PackagePrice> getPackagePrices() {
        return packagePrices;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeliveryPrice that = (DeliveryPrice) o;
        return Objects.equals(packagePrices, that.packagePrices);
    }

    @Override
    public int hashCode() {
        return Objects.hash(packagePrices);
    }

    @Override
    public String toString() {
        return packagePrices.toString();
    }
}
