package com.github.sebyplays.jcommand.utils;

import com.github.sebyplays.coloredconsole.ColoredConsole;
import com.github.sebyplays.jcommand.CommandSender;

public class Console implements CommandSender {

    @Override
    public void sendMessage(String message) {
        ColoredConsole.print(message, true, false, true);
    }

    @Override
    public boolean hasPermission(String permission) {
        return true;
    }

    @Override
    public int getHierarchy() {
        return 10000;
    }

    @Override
    public boolean requiredHierarchy(int hierarchy) {
        return true;
    }

    @Override
    public String getName() {
        return "CONSOLE";
    }
}
