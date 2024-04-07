package com.geektrust.backend.commands;

import java.util.List;
import com.geektrust.backend.entities.Cart;

public class AddProMemberShipCommand implements ICommand{
    private Cart cart;

    public AddProMemberShipCommand(Cart cart) {
        this.cart = cart;
    }

    @Override
    public void execute(List<String> tokens) {
        cart.activateProMembership();
    }
}
