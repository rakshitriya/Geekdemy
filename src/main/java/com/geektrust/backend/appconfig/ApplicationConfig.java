package com.geektrust.backend.appconfig;

import com.geektrust.backend.commands.AddProMemberShipCommand;
import com.geektrust.backend.commands.AddProgrammeCommand;
import com.geektrust.backend.commands.ApplyCouponCommand;
import com.geektrust.backend.commands.CommandInvoker;
import com.geektrust.backend.commands.PrintBillCommand;
import com.geektrust.backend.entities.Cart;
import com.geektrust.backend.services.BillingService;
import com.geektrust.backend.services.ProgrammeService;

public class ApplicationConfig {
    private final CommandInvoker commandInvoker = new CommandInvoker();
    public CommandInvoker getCommandInvoker(){
        Cart cart = new Cart();
        commandInvoker.register("ADD_PROGRAMME", new AddProgrammeCommand(cart, new ProgrammeService()));
        commandInvoker.register("APPLY_COUPON", new ApplyCouponCommand(cart));
        commandInvoker.register("ADD_PRO_MEMBERSHIP", new AddProMemberShipCommand(cart));
        commandInvoker.register("PRINT_BILL", new PrintBillCommand(cart, new BillingService()));
        return commandInvoker;
    }
}
