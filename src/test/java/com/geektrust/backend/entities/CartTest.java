package com.geektrust.backend.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CartTest {

    private Cart cart;

    @BeforeEach
    public void setUp() {
        cart = new Cart();
    }

    @Test
    public void getProgrammes_ShouldReturnEmptyList_WhenCartIsInitialized() {
        Assertions.assertTrue(cart.getProgrammes().isEmpty());
    }

    @Test
    public void isProMembership_ShouldReturnFalse_WhenCartIsInitialized() {
        Assertions.assertFalse(cart.isProMembership());
    }

    @Test
    public void setProMembership_ShouldSetProMembershipCorrectly() {
        cart.activateProMembership();
        Assertions.assertTrue(cart.isProMembership());
    }

    // @Test
    // public void getCoupon_ShouldReturnNull_WhenCartIsInitialized() {
    //     Assertions.assertNull(cart.getCouponCode());
    // }

    @Test
    public void setCoupon_ShouldSetCouponCorrectly() {
        cart.applyCoupon("DEAL_G20");
        Assertions.assertEquals("DEAL_G20", cart.getCouponCode());
    }

    // Add more tests as needed based on the functionality of your Cart class
}
