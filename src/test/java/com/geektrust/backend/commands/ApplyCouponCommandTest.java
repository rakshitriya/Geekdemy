package com.geektrust.backend.commands;

import com.geektrust.backend.entities.Cart;
import com.geektrust.backend.entities.Programme;
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

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DisplayName("ApplyCouponCommandTest")
@ExtendWith(MockitoExtension.class)
public class ApplyCouponCommandTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @Mock
    Cart cart;

    @InjectMocks
    ApplyCouponCommand applyCouponCommand;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    @DisplayName("execute method of ApplyCouponCommand should apply coupon to cart")
    public void execute_ShouldApplyCouponToCart() {
        when(cart.getProgrammes()).thenReturn(Arrays.asList(new Programme("CERTIFICATION", 2), new Programme("DEGREE", 2)));

        applyCouponCommand.execute(Arrays.asList("APPLY_COUPON", "DEAL_G5"));

        Assertions.assertEquals("", outputStreamCaptor.toString().trim());
        verify(cart).applyCoupon("DEAL_G5");
    }

    @Test
    @DisplayName("execute method of ApplyCouponCommand should print error message for invalid arguments")
    public void execute_ShouldPrintErrorMessage_InvalidArguments() {
        applyCouponCommand.execute(Collections.singletonList("APPLY_COUPON"));

        String expectedMessage = "Invalid command format. Usage: APPLY_COUPON <couponname>";
        String actualMessage = outputStreamCaptor.toString().trim();
        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
}

