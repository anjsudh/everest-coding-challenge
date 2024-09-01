package com.courier;

import com.courier.Package;
import com.courier.PricingService;
import com.courier.Delivery;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DeliveryFactory {

    public static Delivery createDelivery(BufferedReader reader) throws IOException {
        String baseAndCountLine = reader.readLine().trim();
        String[] baseAndCount = baseAndCountLine.split(" ");
        double baseDeliveryCost = Double.parseDouble(baseAndCount[0]);
        int numberOfPackages = Integer.parseInt(baseAndCount[1]);

        List<Package> packages = new ArrayList<>();

        for (int i = 0; i < numberOfPackages; i++) {
            String packageLine = reader.readLine().trim();
            String[] packageDetails = packageLine.split(" ");

            String packageId = packageDetails[0];
            double weight = Double.parseDouble(packageDetails[1]);
            double distance = Double.parseDouble(packageDetails[2]);

            Package pkg = new Package(packageId, weight, distance);
            packages.add(pkg);
        }

        return new Delivery(packages, baseDeliveryCost);
    }
}
