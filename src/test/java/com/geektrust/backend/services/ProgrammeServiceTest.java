package com.geektrust.backend.services;

import com.geektrust.backend.entities.Cart;
import com.geektrust.backend.entities.Programme;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProgrammeServiceTest {

    private Cart cart;
    private ProgrammeService programmeService;

    @BeforeEach
    public void setUp() {
        cart = new Cart();
        programmeService = new ProgrammeService();
    }

    @Test
    public void createProgramme_ShouldReturnCorrectProgramme() {
        Programme programme = programmeService.createProgramme("CERTIFICATION");

        Assertions.assertEquals("CERTIFICATION", programme.getType());
        Assertions.assertEquals(3000.0, programme.getPrice());
    }

    @Test
    public void createProgramme_ShouldThrowException_ForInvalidType() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> programmeService.createProgramme("INVALID"));
    }

    @Test
    public void addProgrammeToCart_ShouldAddProgrammeToCart() {
        Programme programme = new Programme("CERTIFICATION", 3000.0);

        programmeService.addProgrammeToCart(cart, programme, 1);

        Assertions.assertEquals(1, cart.getProgrammes().size());
        Assertions.assertEquals(programme, cart.getProgrammes().get(0));
    }

    @Test
    public void addProgrammeToCart_ShouldAddMultipleProgrammesToCart() {
        Programme programme = new Programme("CERTIFICATION", 3000.0);

        programmeService.addProgrammeToCart(cart, programme, 2);

        Assertions.assertEquals(2, cart.getProgrammes().size());
        Assertions.assertEquals(programme, cart.getProgrammes().get(0));
        Assertions.assertEquals(programme, cart.getProgrammes().get(1));
    }
}
