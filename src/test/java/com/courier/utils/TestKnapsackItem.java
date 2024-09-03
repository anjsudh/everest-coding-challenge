package com.courier.utils;

import java.util.Objects;
import com.courier.models.KnapsackItem;

public class TestKnapsackItem implements KnapsackItem {
    private final String name;
    private final int weight;
    private final int value;

    public TestKnapsackItem(String name, int weight, int value) {
        this.name = name;
        this.weight = weight;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    @Override
    public int getWeight() {
        return weight;
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestKnapsackItem that = (TestKnapsackItem) o;
        return weight == that.weight && value == that.value && name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, weight, value);
    }

    @Override
    public String toString() {
        return "TestKnapsackItem{" +
                "name='" + name + '\'' +
                ", weight=" + weight +
                ", value=" + value +
                '}';
    }
}
