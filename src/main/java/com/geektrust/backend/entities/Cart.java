package com.geektrust.backend.entities;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<Programme> programmes;
    private boolean proMembership;
    private String coupon;

    public Cart() {
        this.programmes = new ArrayList<>();
        this.proMembership = false;
    }

    public List<Programme> getProgrammes() {
        return this.programmes;
    }

    public boolean isProMembership() {
        return this.proMembership;
    }

    public void activateProMembership() {
        this.proMembership = true;
    }

    // public String getCoupon() {
    //     return this.coupon;
    // }
    public boolean hasCoupon(String coupon) {
        return this.coupon.equals(coupon);
    }    

    public void applyCoupon(String coupon) {
        this.coupon = coupon;
    }
    public String getCouponCode() {
        return this.coupon != null ? this.coupon : "NONE";
    }
}
