package com.project.airbnbApp.airbnbApp.strategy;

import com.project.airbnbApp.airbnbApp.entity.Inventory;

import java.math.BigDecimal;

public class BasePricingStrategy implements PricingStrategy {
    @Override
    public BigDecimal calculatePrice(Inventory inventory) {
        return inventory.getRoom().getBasePrice();
    }
}
