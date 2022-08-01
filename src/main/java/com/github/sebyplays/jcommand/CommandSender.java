package com.github.sebyplays.jcommand;

public interface CommandSender {

    void sendMessage(String message);
    boolean hasPermission(String permission);
    int getHierarchy();
    boolean requiredHierarchy(int hierarchy);
    String getName();

}
