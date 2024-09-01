package com.courier;

import com.courier.Package;
import com.courier.Delivery;
import com.courier.PricingService;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeliveryFactoryTest {

    @Test
    public void shouldParseInputAndCreateDelivery() {
        String input = """
                        100 3
                        PKG1 5 5
                        PKG2 15 5
                        PKG3 10 100
                        """;

        InputStream inputStream = new ByteArrayInputStream(input.getBytes());

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            Delivery actualDelivery = DeliveryFactory.createDelivery(reader);

            double expectedBaseCost = 100.0;
            List<Package> expectedPackages = List.of(
                new Package("PKG1", 5, 5),
                new Package("PKG2", 15, 5),
                new Package("PKG3", 10, 100)
            );

            Delivery expectedDelivery = new Delivery(expectedPackages, expectedBaseCost);

            assertEquals(expectedDelivery, actualDelivery);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
