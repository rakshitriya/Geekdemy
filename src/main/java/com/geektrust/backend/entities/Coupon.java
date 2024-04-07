package com.geektrust.backend.entities;

public class Coupon {
    private final String name;
    private final double discount;
    public Coupon(String name, double discount) {
        this.name = name;
        this.discount = discount;
    }

    public String getName() {
        return name;
    }
    public double getDiscount() {
        return discount;
    }
    @Override
    public String toString(){
        return "Coupon =" + getName() + " Discount =" + getDiscount();
    }
}
