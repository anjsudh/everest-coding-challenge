package com.courier;

public class Package {

    private String name;      // Name of the package
    private double weight;    // Weight of the package in kilograms
    private double distance;  // Distance to destination in kilometers

    public Package(String name, double weight, double distance) {
        this.name = name;
        this.weight = weight;
        this.distance = distance;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
}
