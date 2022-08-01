package com.github.sebyplays.jcommand;

import com.github.sebyplays.jcommand.utils.Command;

public interface CommandExecutor {

    boolean onCommand(CommandSender commandSender, Command command, String[] args);

}
