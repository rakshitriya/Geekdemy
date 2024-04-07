package com.geektrust.backend.commands;

import java.util.List;
import com.geektrust.backend.entities.Cart;
import com.geektrust.backend.entities.Programme;
import com.geektrust.backend.exceptions.InvalidOperationException;
import com.geektrust.backend.services.ProgrammeService;

public class AddProgrammeCommand implements ICommand{
    private Cart cart;
    private ProgrammeService programmeService;
    private static final int EXPECTED_TOKENS_SIZE = 3;
    private static final int TYPE_INDEX = 1;
    private static final int QUANTITY_INDEX = 2;

    public AddProgrammeCommand(Cart cart, ProgrammeService programmeService) {
        this.cart = cart;
        this.programmeService = programmeService;
    }

    @Override
    public void execute(List<String> tokens) {
        try {
            performExecution(tokens);
        } catch (InvalidOperationException | NumberFormatException e) {
            System.out.println(e.getMessage());
        } 
    }
    
    public void performExecution(List<String> tokens) {
        if (tokens.size() != EXPECTED_TOKENS_SIZE) {
            throw new InvalidOperationException("Invalid command format. Usage: ADD_PROGRAMME <programmename> <quantity>");
        }
    
        String type = tokens.get(TYPE_INDEX);
        int quantity;
    
        try {
            quantity = Integer.parseInt(tokens.get(QUANTITY_INDEX));
        } catch (NumberFormatException e) {
            throw new InvalidOperationException("Invalid quantity. Quantity should be a valid integer.");
        }
    
        if (quantity < 0) {
            throw new InvalidOperationException("Invalid quantity. Quantity cannot be negative.");
        }
    
        Programme programme = programmeService.createProgramme(type);
        programmeService.addProgrammeToCart(cart, programme, quantity);
    }
}
