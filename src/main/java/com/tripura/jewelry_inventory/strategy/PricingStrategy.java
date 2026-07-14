package com.tripura.jewelry_inventory.strategy;

import java.math.BigDecimal;

public interface PricingStrategy {
    BigDecimal calculateTotalPrice(BigDecimal weightInGrams,
                                   BigDecimal ratePerGram,
                                   BigDecimal makingChargePercentage);
}
