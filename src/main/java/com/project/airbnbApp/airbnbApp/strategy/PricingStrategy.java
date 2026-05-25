package com.project.airbnbApp.airbnbApp.strategy;

import com.project.airbnbApp.airbnbApp.entity.Inventory;

import java.math.BigDecimal;

public interface PricingStrategy {
    BigDecimal calculatePrice(Inventory inventory);
}
