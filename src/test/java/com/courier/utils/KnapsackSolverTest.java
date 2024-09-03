package com.courier.utils;

import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.stream.Collectors;

import com.courier.models.KnapsackItem;
import com.courier.utils.TestKnapsackItem;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class KnapsackSolverTest {

    @Test
    public void testSelectItemsForKnapsack() {
        // Create a list of TestKnapsackItem
        List<TestKnapsackItem> items = List.of(
                new TestKnapsackItem("Item1", 1, 2),
                new TestKnapsackItem("Item2", 2, 3),
                new TestKnapsackItem("Item3", 3, 4),
                new TestKnapsackItem("Item4", 8, 8),
                new TestKnapsackItem("Item5", 7, 7)
        );
        List<KnapsackItem> knapsackItems = items.stream()
                .map(item -> (KnapsackItem) item)  // Casting Package to KnapsackItem
                .collect(Collectors.toList());

        int capacity = 10;

        // Call the method to test
        List<KnapsackItem> selectedItems = KnapsackSolver.selectItemsForKnapsack(knapsackItems, capacity);
        List<TestKnapsackItem> actualItems = selectedItems.stream()
                .map(item -> (TestKnapsackItem) item)
                .collect(Collectors.toList());

        // Define the expected items to be selected
        List<TestKnapsackItem> expectedItems = List.of(
                new TestKnapsackItem("Item1", 1, 2),
                new TestKnapsackItem("Item2", 2, 3),
                new TestKnapsackItem("Item3", 3, 4),
                new TestKnapsackItem("Item4", 8, 8)
        );

        // Check that the selected items match the expected items
        assertEquals(expectedItems, actualItems);
    }
}
