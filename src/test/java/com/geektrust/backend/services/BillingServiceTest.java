package com.geektrust.backend.services;

import com.geektrust.backend.entities.Cart;
import com.geektrust.backend.entities.Programme;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class BillingServiceTest {

    private BillingService billingService;

    @Mock
    private Cart cart;

    @Mock
    private Programme programme1;

    @Mock
    private Programme programme2;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        billingService = new BillingService();
    }

    @Test
    public void calculateProDiscount_shouldCalculateDiscount_whenCartHasProMembership() {
        when(cart.isProMembership()).thenReturn(true);
        when(cart.getProgrammes()).thenReturn(Arrays.asList(programme1, programme2));
        when(programme1.getType()).thenReturn("DEGREE");
        when(programme1.getPrice()).thenReturn(5000.0);
        when(programme2.getType()).thenReturn("CERTIFICATION");
        when(programme2.getPrice()).thenReturn(3000.0);

        double discount = billingService.calculateProDiscount(cart);

        assertEquals(210.0, discount);
    }

    @Test
    public void cheapestProgrammePriceforB4G1_shouldReturnCheapestProgrammePrice() {
        when(cart.getProgrammes()).thenReturn(Arrays.asList(programme1, programme2));
        when(programme1.getType()).thenReturn("DEGREE");
        when(programme1.getPrice()).thenReturn(5000.0);
        when(programme2.getType()).thenReturn("CERTIFICATION");
        when(programme2.getPrice()).thenReturn(3000.0);

        double cheapestProgrammePrice = billingService.cheapestProgrammePriceforB4G1(cart);

        assertEquals(2940.0, cheapestProgrammePrice);
    }
}
