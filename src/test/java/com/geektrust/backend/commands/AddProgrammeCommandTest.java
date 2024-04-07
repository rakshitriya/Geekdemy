package com.geektrust.backend.commands;

import com.geektrust.backend.entities.Cart;
import com.geektrust.backend.entities.Programme;
import com.geektrust.backend.exceptions.InvalidOperationException;
import com.geektrust.backend.services.ProgrammeService;
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
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DisplayName("AddProgrammeCommandTest")
@ExtendWith(MockitoExtension.class)
public class AddProgrammeCommandTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @Mock
    Cart cart;

    @Mock
    ProgrammeService programmeService;

    @InjectMocks
    AddProgrammeCommand addProgrammeCommand;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    @DisplayName("execute method of AddProgrammeCommand should add programme to cart")
    public void execute_ShouldAddProgrammeToCart() {
        Programme programme = new Programme("CERTIFICATION", 3000);
        when(programmeService.createProgramme(any())).thenReturn(programme);

        List<String> tokens = Arrays.asList("ADD_PROGRAMME", "CERTIFICATION", "2");
        addProgrammeCommand.execute(tokens);

        Assertions.assertEquals("", outputStreamCaptor.toString().trim());
        verify(programmeService, times(1)).createProgramme("CERTIFICATION");
        verify(programmeService, times(1)).addProgrammeToCart(cart, programme, 2);
    }

    @Test
    @DisplayName("execute method of AddProgrammeCommand should print error message for invalid arguments")
    public void execute_ShouldPrintErrorMessage_InvalidArguments() {
        List<String> tokens = Arrays.asList("ADD_PROGRAMME", "DEGREE");
        Exception exception = assertThrows(InvalidOperationException.class, () -> {
            addProgrammeCommand.performExecution(tokens);
        });
        String expectedMessage = "Invalid command format. Usage: ADD_PROGRAMME <programmename> <quantity>";
        String actualMessage = exception.getMessage();
        Assertions.assertTrue(actualMessage.contains(expectedMessage));

    }

    @Test
    @DisplayName("execute method of AddProgrammeCommand should print error message for invalid quantity")
    public void execute_ShouldPrintErrorMessage_InvalidQuantity() {
        List<String> tokens = Arrays.asList("ADD_PROGRAMME", "DEGREE", "-7");
        Exception exception = assertThrows(InvalidOperationException.class, () -> {
            addProgrammeCommand.performExecution(tokens);
        });

        String expectedMessage = "Invalid quantity. Quantity cannot be negative.";
        String actualMessage = exception.getMessage();

    Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
}
