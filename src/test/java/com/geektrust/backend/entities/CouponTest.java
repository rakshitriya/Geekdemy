package com.geektrust.backend.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CouponTest {
    @Test
    public void createCoupon_ShouldReturnCorrectNameAndDiscount() {
        Coupon coupon = new Coupon("DEAL_G20", 0.2);

        Assertions.assertEquals("DEAL_G20", coupon.getName());
        Assertions.assertEquals(0.2, coupon.getDiscount());
    }

    @Test
    public void toString_ShouldReturnFormattedString() {
        Coupon coupon = new Coupon("DEAL_G20", 0.2);

        String expectedString = "Coupon =DEAL_G20 Discount =0.2";
        Assertions.assertEquals(expectedString, coupon.toString());
    }

}
