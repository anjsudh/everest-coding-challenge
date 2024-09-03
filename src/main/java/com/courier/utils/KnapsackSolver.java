package com.courier.utils;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import com.courier.models.KnapsackItem;

public class KnapsackSolver {

    public static List<KnapsackItem> selectItemsForKnapsack(List<KnapsackItem> items, int capacity) {
        int n = items.size();
        double[][] dp = new double[n + 1][capacity + 1];
        boolean[][] keep = new boolean[n + 1][capacity + 1];

        for (int i = 1; i <= n; i++) {
            KnapsackItem item = items.get(i - 1);
            int weight = item.getWeight();
            int value = item.getValue();
            for (int w = 0; w <= capacity; w++) {
                if (weight <= w) {
                    if (dp[i - 1][w] < dp[i - 1][w - weight] + value) {
                        dp[i][w] = dp[i - 1][w - weight] + value;
                        keep[i][w] = true;
                    } else {
                        dp[i][w] = dp[i - 1][w];
                    }
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        List<KnapsackItem> selectedItems = new ArrayList<>();
        int w = capacity;
        for (int i = n; i > 0; i--) {
            if (keep[i][w]) {
                selectedItems.add(items.get(i - 1));
                w -= items.get(i - 1).getWeight();
            }
        }

        Collections.reverse(selectedItems);
        return selectedItems;
    }
}
