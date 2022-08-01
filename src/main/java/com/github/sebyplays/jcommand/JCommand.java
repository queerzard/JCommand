package com.github.sebyplays.jcommand;

import com.github.sebyplays.coloredconsole.ColoredConsole;
import com.github.sebyplays.jcommand.utils.Command;
import com.github.sebyplays.jcommand.utils.Console;
import com.github.sebyplays.jcommand.utils.PreConditions;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

import java.util.HashMap;
import java.util.Scanner;

/**
 * It creates a new thread that runs a loop that waits for user input, then calls the command with the input
 */
public class JCommand {

    @Getter private HashMap<String, Command> commands = new HashMap<>();
    @Getter @Setter private String inputPrefix = "Input";
    @Getter private final Scanner scanner = new Scanner(System.in);

    public JCommand(){
        launchListeners();
    }

    /**
     * It creates a new thread that runs a loop that waits for user input, then calls the command with the input
     */
    public void launchListeners(){
        new Thread("commandListener"){
            @Override
            public void run() {
                while (true){
                    System.out.print(getInputPrefix() + " > ");
                    callCommand(new Console(), getInput());
                }
            }
        }.start();
    }

    /**
     * It registers a command
     *
     * @param commandName The name of the command.
     * @param description The description of the command.
     * @param commandExecutor This is the class that will be called when the command is executed.
     * @return A boolean value.
     */
    public boolean registerCommand(String commandName, String description, CommandExecutor commandExecutor){
        if(!commands.containsKey(commandName.toLowerCase())){
            this.commands.put(commandName, new Command(commandName, description, commandExecutor));
            return true;
        }
        throw new IllegalArgumentException("command already registered");
    }

    /**
     * It removes a command from the command list
     *
     * @param commandName The name of the command you want to unregister.
     */
    @SneakyThrows
    public void unregisterCommand(String commandName){
        if(commands.containsKey(commandName.toLowerCase())){
            this.commands.remove(commandName);
            return;
        }
        ColoredConsole.print("No such command found!", true, false, true);
    }

    /**
     * It takes a CommandSender and a String as input, splits the String into an array of Strings, checks if the first
     * element of the array is a registered command, and if so, calls the executor of the command with the CommandSender
     * and the array of Strings as input
     *
     * @param commandSender The CommandSender who called the command.
     * @param commandName The command name with the arguments.
     */
    public void callCommand(CommandSender commandSender, String commandName){
        if(PreConditions.notNull(commandName)){
            ColoredConsole.print("No valid input found", true, false, true);
            return;
        }
        String[] temp = commandName.split(" ");
        String strTemp = commandName.replace(temp[0] + " ", "");
        String[] args = strTemp.split(" ");

        if(commands.containsKey(temp[0].toLowerCase())){
            commands.get(temp[0].toLowerCase()).callExecutor(commandSender, args);
            return;
        }
        ColoredConsole.print("Sorry, \"" + temp[0] + "\" seems not to be registered.", false, false, true);
        return;
    }

    /**
     * It returns the description of the command with the name passed in as a parameter
     *
     * @param commandName The name of the command you want to get the description of.
     * @return The description of the command.
     */
    public String getDescription(String commandName){
        return commands.get(commandName).getDescription();
    }

    /**
     * It returns the next line of input from the user
     *
     * @return The next line of input from the user.
     */
    public String getInput(){
        return scanner.nextLine();
    }
}
