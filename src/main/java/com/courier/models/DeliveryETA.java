package com.courier.models;

import java.util.List;
import java.util.Objects;

public class DeliveryETA {
    private final List<PackageETA> packageETAs;

    public DeliveryETA(List<PackageETA> packageETAs) {
        this.packageETAs = packageETAs;
    }

    public List<PackageETA> getPackageETAs() {
        return packageETAs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeliveryETA that = (DeliveryETA) o;
        return Objects.equals(packageETAs, that.packageETAs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(packageETAs);
    }

    @Override
    public String toString() {
        return packageETAs.toString();
    }
}
