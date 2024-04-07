package com.geektrust.backend.services;

import java.util.Comparator;
import com.geektrust.backend.entities.Cart;
import com.geektrust.backend.entities.Programme;

public class BillingService {
    private final double CERTIFICATION_DISCOUNT_PERCENT = 0.02;
    private final double DIPLOMA_DISCOUNT_PERCENT = 0.01;
    private final double DEGREE_DISCOUNT_PERCENT = 0.03;
    private final double DEAL_G20_MIN_PRICE = 10000;
    private final double DEAL_G20_DISCOUNT_PERCENT = 0.2;
    private final double DEAL_G5_DISCOUNT_PERCENT = 0.05;
    private final double ENROLLMENT_FEE_MIN_PRICE = 6666;
    private final double ENROLLMENT_FEE = 500;
    private final int DEAL_G5_DISCOUNT_MIN_ITEMS = 2;
    private final int B4G1_DISCOUNT_MIN_ITEMS = 4;

    public double calculateProDiscount(Cart cart) {
        return calculateProgrammeDiscount(cart, true);
    }
    public double cheapestProgrammePriceforB4G1(Cart cart){
        Programme cheapestProgramme = getCheapestProgramme(cart);
        if (cheapestProgramme == null) {
            return 0;
        }
        
        return calculateProgrammeDiscount(cheapestProgramme, false);
    }
    private double calculateProgrammeDiscount(Cart cart, boolean isProDiscount) {
        if (!cart.isProMembership()) {
            return 0;
        }
    
        double discount = 0;
        for (Programme programme : cart.getProgrammes()) {
            discount += calculateProgrammeDiscount(programme, isProDiscount);
        }
        return discount;
    }
    
    private double calculateProgrammeDiscount(Programme programme, boolean isProDiscount) {
        double discountRate = 0;
        switch (programme.getType()) {
            case "CERTIFICATION":
                discountRate = CERTIFICATION_DISCOUNT_PERCENT;
                break;
            case "DEGREE":
                discountRate = DEGREE_DISCOUNT_PERCENT;
                break;
            case "DIPLOMA":
                discountRate = DIPLOMA_DISCOUNT_PERCENT;
                break;
        }
        if (isProDiscount) {
            return programme.getPrice() * discountRate;
        } 
        return programme.getPrice() - (programme.getPrice() * discountRate);
    }
    
    public Programme getCheapestProgramme(Cart cart){
        return cart.getProgrammes().stream()
            .min(Comparator.comparing(Programme::getPrice))
            .orElse(null);
    }
    

    // Calculate coupon discount based on cart and subtotal
    public double calculateCouponDiscount(Cart cart, double subtotal) {
        String coupon = cart.getCouponCode();
        if (coupon == null) {
            return 0;
        }
        switch (coupon) {
            case "B4G1":
                return calculateB4G1Discount(cart);
            case "DEAL_G20":
                return calculateDealG20Discount(subtotal);
            case "DEAL_G5":
                return calculateDealG5Discount(cart, subtotal);
            default:
                return 0;
        }
    }

    // Calculate B4G1 discount
    private double calculateB4G1Discount(Cart cart) {
        if (cart.getProgrammes().size() < B4G1_DISCOUNT_MIN_ITEMS ) {
            return 0;
        }
        if (cart.isProMembership()) {
            return cheapestProgrammePriceforB4G1(cart);
        }
        return cart.getProgrammes().stream()
                .min(Comparator.comparing(Programme::getPrice))
                .map(Programme::getPrice)
                .orElse(0.0);
    }
    // Calculate DEAL_G20 discount
    private double calculateDealG20Discount(double subtotal) {
        if (subtotal < DEAL_G20_MIN_PRICE) {
            return 0;
        }

        return subtotal * DEAL_G20_DISCOUNT_PERCENT;
    }
    // Calculate DEAL_G5 discount
    private double calculateDealG5Discount(Cart cart, double subtotal) {
        if (cart.getProgrammes().size() < DEAL_G5_DISCOUNT_MIN_ITEMS) {
            return 0;
        }

        return subtotal * DEAL_G5_DISCOUNT_PERCENT;
    }
    public double calculateEnrollmentFee(double subtotal) {
        return subtotal < ENROLLMENT_FEE_MIN_PRICE ? ENROLLMENT_FEE : 0;
    }
}
