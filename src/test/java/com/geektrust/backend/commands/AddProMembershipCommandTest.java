package com.geektrust.backend.commands;

import com.geektrust.backend.entities.Cart;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Collections;

import static org.mockito.Mockito.verify;

@DisplayName("AddProMembershipCommandTest")
@ExtendWith(MockitoExtension.class)
public class AddProMembershipCommandTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @Mock
    Cart cart;

    @InjectMocks
    AddProMemberShipCommand addProMembershipCommand;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    @DisplayName("execute method of AddProMembershipCommand should add Pro membership to cart")
    public void execute_ShouldAddProMembershipToCart() {
        addProMembershipCommand.execute(Collections.emptyList());
        verify(cart).activateProMembership();
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
}
