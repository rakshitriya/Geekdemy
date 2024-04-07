package com.geektrust.backend;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import com.geektrust.backend.appconfig.ApplicationConfig;
import com.geektrust.backend.commands.CommandInvoker;
import com.geektrust.backend.exceptions.NoSuchCommandException;

public class App {

	public static void main(String[] args) {
		// List<String> commandLineArgs = new LinkedList<>(Arrays.asList(args));
        // String expectedSequence = "INPUT_FILE";
        // String actualSequence = commandLineArgs.stream()
        //         .map(a -> a.split("=")[0])
        //         .collect(Collectors.joining("$"));
        // if(expectedSequence.equals(actualSequence)){
        //     run(commandLineArgs);
        // }
        run(args);
	}
	public static void run(String[] commandLineArgs) {
    //public static void run(List<String> commandLineArgs) {
        ApplicationConfig applicationConfig = new ApplicationConfig();
        CommandInvoker commandInvoker = applicationConfig.getCommandInvoker();
        BufferedReader reader;
        String inputFile = commandLineArgs[0];
        //String inputFile = commandLineArgs.get(0).split("=")[1];
        // commandLineArgs.remove(0);
        try {
            reader = new BufferedReader(new FileReader(inputFile));
            String line = reader.readLine();
            while (line != null) {
                List<String> tokens = Arrays.asList(line.split(" "));
                commandInvoker.executeCommand(tokens.get(0),tokens);
                // read next line
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException | NoSuchCommandException e) {
            e.printStackTrace();
        }

   }

}
