package com.courier;

import java.util.Objects;
import java.text.DecimalFormat;

public class PackagePrice {
    private final Package pkg;
    private final double price;
    private final double discount;

    public PackagePrice(Package pkg, double price, double discount) {
        this.pkg = pkg;
        this.price = price;
        this.discount = discount;
    }

    public Package getPackage() {
        return pkg;
    }

    public double getPrice() {
        return price;
    }

    public double getDiscount() {
        return discount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PackagePrice that = (PackagePrice) o;
        return (
            Double.compare(that.price, price) == 0 
            && Objects.equals(pkg, that.pkg)
            && Double.compare(that.discount, discount) == 0 
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(pkg, price, discount);
    }

    @Override
    public String toString() {
        return pkg.getId() + " " 
        + new DecimalFormat("#.##").format(discount)+ " " 
        + new DecimalFormat("#.##").format(price);
        
        // String.format("%s %.1f %.1f", pkg.getId(), discount, price);
    }
}
