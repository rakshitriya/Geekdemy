package com.geektrust.backend.commands;

import com.geektrust.backend.entities.Cart;
import com.geektrust.backend.entities.Programme;
import com.geektrust.backend.services.BillingService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Collections;

import static org.mockito.Mockito.when;

@DisplayName("PrintBillCommandTest")
@ExtendWith(MockitoExtension.class)
public class PrintBillCommandTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @Mock
    Cart cart;

    @Mock
    BillingService billingService;

    @InjectMocks
    PrintBillCommand printBillCommand;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    @DisplayName("execute method of PrintBillCommand should print the bill for specific input")
    public void execute_ShouldPrintBill_SpecificInput() {
        when(cart.getProgrammes()).thenReturn(Arrays.asList(
                new Programme("CERTIFICATION", 3000.0),
                new Programme("DEGREE", 5000.0),
                new Programme("DIPLOMA", 2 * 2500.0)
        ));
        when(cart.isProMembership()).thenReturn(false);
        when(cart.getCouponCode()).thenReturn("B4G1");
        when(billingService.calculateProDiscount(cart)).thenReturn(0.0);
        when(billingService.calculateCouponDiscount(cart, 13000.0)).thenReturn(2500.0);
        when(billingService.calculateEnrollmentFee(10500.0)).thenReturn(0.0);

        printBillCommand.execute(Collections.emptyList());

        String expectedOutput = "SUB_TOTAL 13000.00\n" +
                "COUPON_DISCOUNT B4G1 2500.00\n" +
                "TOTAL_PRO_DISCOUNT 0.00\n" +
                "PRO_MEMBERSHIP_FEE 0.00\n" +
                "ENROLLMENT_FEE 0.00\n" +
                "TOTAL 10500.00";
        Assertions.assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
    }
    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
}

