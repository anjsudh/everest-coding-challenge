package com.courier;

import java.util.Objects;

public class PackagePrice {
    private final Package pkg;
    private final double price;

    public PackagePrice(Package pkg, double price) {
        this.pkg = pkg;
        this.price = price;
    }

    public Package getPackage() {
        return pkg;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PackagePrice that = (PackagePrice) o;
        return Double.compare(that.price, price) == 0 && Objects.equals(pkg, that.pkg);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pkg, price);
    }

    @Override
    public String toString() {
        return String.format("%s %.1f", pkg.getId(), price);
    }
}
