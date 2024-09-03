package com.courier.factories;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.courier.models.Vehicle;

public class VehicleFactory {

    public static List<Vehicle> createVehicles(BufferedReader reader) throws IOException {
        List<Vehicle> vehicles = new ArrayList<>();

        String line = reader.readLine();
        if (line == null) {
            throw new IOException("Vehicle input is missing");
        }

        String[] parts = line.split("\\s+");
        if (parts.length != 3) {
            throw new IllegalArgumentException("Invalid vehicle input format");
        }

        int numberOfVehicles = Integer.parseInt(parts[0]);
        double maxSpeed = Double.parseDouble(parts[1]);
        double maxCarriableWeight = Double.parseDouble(parts[2]);

        for (int i = 0; i < numberOfVehicles; i++) {
            vehicles.add(new Vehicle(Integer.toString(i + 1), maxCarriableWeight, maxSpeed));
        }

        return vehicles;
    }
}
