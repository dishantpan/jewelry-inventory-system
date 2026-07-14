package com.tripura.jewelry_inventory.strategy;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class StandardPricingStrategy implements PricingStrategy {

    @Override
    public BigDecimal calculateTotalPrice(BigDecimal weightInGrams,
                                          BigDecimal ratePerGram,
                                          BigDecimal makingChargePercentage) {
        BigDecimal makingChargeMultiplier = BigDecimal.ONE
                .add(makingChargePercentage.divide(BigDecimal.valueOf(100)));
        return weightInGrams
                .multiply(ratePerGram)
                .multiply(makingChargeMultiplier)
                .setScale(2, RoundingMode.HALF_UP);
    }
}
