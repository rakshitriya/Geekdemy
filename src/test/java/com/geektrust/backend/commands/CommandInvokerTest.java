package com.geektrust.backend.commands;


import static org.mockito.ArgumentMatchers.anyList;

import java.util.ArrayList;
import com.geektrust.backend.exceptions.NoSuchCommandException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


@DisplayName("CommandInvokerTest")
@ExtendWith(MockitoExtension.class)
public class CommandInvokerTest {
    private CommandInvoker commandInvoker;

    @Mock
    AddProgrammeCommand addProgrammeCommand;

    @Mock
    AddProMemberShipCommand addProMemberShipCommand;

    @Mock
    ApplyCouponCommand applyCouponCommand;

    @Mock
    PrintBillCommand printBillCommand;

    @BeforeEach
    void setup(){
        commandInvoker = new CommandInvoker();
        commandInvoker.register("ADD_PROGRAMME",addProgrammeCommand);
        commandInvoker.register("APPLY_COUPON",addProMemberShipCommand);
        commandInvoker.register("ADD_PRO_MEMBERSHIP",applyCouponCommand);
        commandInvoker.register("PRINT_BILL",printBillCommand);
    }

    @Test
    @DisplayName("executeCommand method Should Execute Command Given CommandName and List of tokens")
    public void executeCommand_GivenNameAndTokens_ShouldExecuteCommand() {
        commandInvoker.executeCommand("ADD_PROGRAMME",anyList());
        commandInvoker.executeCommand("APPLY_COUPON",anyList());
        commandInvoker.executeCommand("ADD_PRO_MEMBERSHIP",anyList());
        commandInvoker.executeCommand("PRINT_BILL",anyList());
    }

    @Test
    @DisplayName("executeCommand method Should Throw Exception Given Incorrect CommandName and List of tokens")
    public void executeCommand_GivenIncorrectNameAndTokens_ShouldThrowException() {
        //Act and Assert
        Assertions.assertThrows(NoSuchCommandException.class,() -> commandInvoker.executeCommand("RANDOM-COMMAND",new ArrayList<String>()));

    }


}

