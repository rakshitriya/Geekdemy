package com.geektrust.backend.commands;

import java.util.List;
import com.geektrust.backend.entities.Cart;
import com.geektrust.backend.entities.Programme;
import com.geektrust.backend.services.BillingService;

public class PrintBillCommand implements ICommand {
    private Cart cart;
    private BillingService billingService;
    private static final double PRO_MEMBERSHIP_FEE = 200.00;


    public PrintBillCommand(Cart cart, BillingService billingService) {
        this.cart = cart;
        this.billingService = billingService;
    }

    @Override
    public void execute(List<String> tokens) {
        double totalProgramFee = cart.getProgrammes().stream()
                .mapToDouble(Programme::getPrice)
                .sum();
        double proMembershipFee = 0.00;
        if (cart.isProMembership()) {
            proMembershipFee = PRO_MEMBERSHIP_FEE;
            totalProgramFee += proMembershipFee; // Adding pro membership fee
        }

        double proDiscount = billingService.calculateProDiscount(cart);
        double subtotal = totalProgramFee - proDiscount; 
        double couponDiscount = billingService.calculateCouponDiscount(cart, subtotal);
        System.out.println("SUB_TOTAL " + String.format("%.2f", subtotal));
        // if(cart.getCoupon() == null){
        //     System.out.println("COUPON_DISCOUNT NONE " + String.format("%.2f", couponDiscount));
        // }
        // else {
        //     System.out.println("COUPON_DISCOUNT " + cart.getCoupon()+ " " + String.format("%.2f", couponDiscount));
        // }
        System.out.println("COUPON_DISCOUNT " + cart.getCouponCode() + " " + String.format("%.2f", couponDiscount));
        System.out.println("TOTAL_PRO_DISCOUNT " + String.format("%.2f", proDiscount));
        System.out.println("PRO_MEMBERSHIP_FEE "+ String.format("%.2f", proMembershipFee));
        subtotal -= couponDiscount;
        double enrollmentFee = billingService.calculateEnrollmentFee(subtotal);
        System.out.println("ENROLLMENT_FEE " + String.format("%.2f", enrollmentFee));
        double total = subtotal + enrollmentFee;
        System.out.println("TOTAL " + String.format("%.2f", total));
    }   
}
