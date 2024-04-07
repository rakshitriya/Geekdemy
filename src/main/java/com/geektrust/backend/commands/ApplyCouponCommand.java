package com.geektrust.backend.commands;

import java.util.List;
import com.geektrust.backend.entities.Cart;
import com.geektrust.backend.entities.Programme;
import com.geektrust.backend.exceptions.InvalidOperationException;

public class ApplyCouponCommand implements ICommand{
    private Cart cart;
    private final double DEAL_G20_MIN_PRICE = 10000;
    private final int B4G1_DISCOUNT_MIN_ITEMS = 4;
    private static final int EXPECTED_TOKEN_SIZE = 2;

    public ApplyCouponCommand(Cart cart) {
        this.cart = cart;
    }
    @Override
    public void execute(List<String> tokens) {
        try {
            if (tokens.size() != EXPECTED_TOKEN_SIZE) {
                throw new InvalidOperationException("Invalid command format. Usage: APPLY_COUPON <couponname>");
            }

            double subtotal =0;
            for (Programme programme : cart.getProgrammes()) {
                subtotal += programme.getPrice();
            }

            // If there are 4 or more programs, B4G1 is auto applied
            if (cart.getProgrammes().size() >= B4G1_DISCOUNT_MIN_ITEMS) {
                cart.applyCoupon("B4G1");
                return;
            }

            String appliedCoupon = cart.getCouponCode();
            for (String coupon : tokens.subList(1, tokens.size())) {
                if ("DEAL_G20".equals(coupon) && subtotal >= DEAL_G20_MIN_PRICE && !"DEAL_G20".equals(appliedCoupon)) {
                    appliedCoupon = coupon;
                } else if ("DEAL_G5".equals(coupon) && cart.getProgrammes().size() >= 2 && !"DEAL_G20".equals(appliedCoupon)) {
                    appliedCoupon = coupon;
                }
            }

            cart.applyCoupon(appliedCoupon);
        } catch (InvalidOperationException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
