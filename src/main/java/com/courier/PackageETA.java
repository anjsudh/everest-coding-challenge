package com.courier;

import java.util.Objects;

public class PackageETA {
    private final Package pkg;
    private final double eta;

    public PackageETA(Package pkg, double eta) {
        this.pkg = pkg;
        this.eta = eta;
    }

    public Package getPackage() {
        return pkg;
    }

    public double getEta() {
        return eta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PackageETA that = (PackageETA) o;
        return Double.compare(that.eta, eta) == 0 && pkg.equals(that.pkg);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pkg, eta);
    }

    @Override
    public String toString() {
        return String.format("%s %.2f", pkg.getId(), eta);
    }
}
