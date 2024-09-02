package com.courier;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.StringReader;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class VehicleFactoryTest {

    @Test
    public void testCreateVehiclesValidInput() throws IOException {
        String input = "3 80 150\n"; // 3 vehicles, speed 80, capacity 150
        BufferedReader reader = new BufferedReader(new StringReader(input));

        List<Vehicle> vehicles = VehicleFactory.createVehicles(reader);

        assertEquals(3, vehicles.size(), "Should create 3 vehicles");
        for (int i = 0; i < vehicles.size(); i++) {
            Vehicle vehicle = vehicles.get(i);
            assertEquals(Integer.toString(i + 1), vehicle.getName(), "Vehicle name should match");
            assertEquals(150, vehicle.getCapacity(), "Vehicle capacity should be 150");
            assertEquals(80, vehicle.getSpeed(), "Vehicle speed should be 80");
        }
    }

    @Test
    public void testCreateVehiclesInvalidFormat() {
        String input = "2 100\n"; // Invalid format
        BufferedReader reader = new BufferedReader(new StringReader(input));

        IOException thrown = assertThrows(IOException.class, () -> {
            VehicleFactory.createVehicles(reader);
        });

        assertEquals("Vehicle input is missing", thrown.getMessage());
    }

    @Test
    public void testCreateVehiclesInvalidNumberFormat() {
        String input = "two 100 150\n"; // Invalid number format
        BufferedReader reader = new BufferedReader(new StringReader(input));

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            VehicleFactory.createVehicles(reader);
        });

        assertEquals("Invalid vehicle input format", thrown.getMessage());
    }
}
