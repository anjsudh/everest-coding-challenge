package com.courier;

public class Main {

    public static void main(String[] args) {
        try {
            String name = args[0];
            double baseCost = Double.parseDouble(args[1]);
            double weight = Double.parseDouble(args[2]);
            double distance = Double.parseDouble(args[3]);
            Package pkg = new Package(name, weight, distance);
            Delivery delivery = new Delivery(baseCost, pkg);
            double totalCost = delivery.calculateTotalCost();
            System.out.printf("'%s' $%.2f%n", name, totalCost);

        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please provide numeric values for weight and distance.");
        }
    }
}
