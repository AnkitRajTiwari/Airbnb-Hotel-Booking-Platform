package com.project.airbnbApp.airbnbApp.service;

import com.project.airbnbApp.airbnbApp.entity.Booking;

public interface CheckoutService {
    String getCheckoutSession(Booking booking, String successUrl, String failureUrl);
}
