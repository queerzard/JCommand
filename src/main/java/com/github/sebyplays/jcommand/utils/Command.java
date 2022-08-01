package com.github.sebyplays.jcommand.utils;

import com.github.sebyplays.jcommand.CommandExecutor;
import com.github.sebyplays.jcommand.CommandSender;
import lombok.Getter;

public class Command {

    @Getter private String command;
    @Getter private String description;
    @Getter public CommandExecutor commandExecutor;

    public Command(String commandName, String description, CommandExecutor commandExecutor){
        this.command = commandName;
        this.description = description;
        this.commandExecutor = commandExecutor;
    }

    /**
     * It calls the command executor
     *
     * @param commandSender The CommandSender that is executing the command.
     * @param args The arguments passed to the command.
     * @return A boolean value.
     */
    public boolean callExecutor(CommandSender commandSender, String[] args){
        return this.commandExecutor.onCommand(commandSender, this, args);
    }

}
