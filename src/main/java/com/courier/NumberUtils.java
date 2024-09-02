package com.courier;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class NumberUtils {

    public static double floorToTwoDecimalPlaces(double value) {
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(2, RoundingMode.FLOOR); 
        return bd.doubleValue(); 
    }
}
