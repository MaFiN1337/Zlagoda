package com.example.controller;

import com.example.controller.command.Command;
import com.example.controller.command.PageNotFoundCommand;
public enum CommandEnum {

    PAGE_NOT_FOUND {
        {
            this.key = "GET:pageNotFound";
            this.command = new PageNotFoundCommand();
        }
    };

    String key;
    Command command;

    public Command getCommand() {
        return command;
    }

    public String getKey() {
        return key;
    }

    public static Command getCommand(String key) {
        for (final CommandEnum command : CommandEnum.values()) {
            if (command.getKey().equals(key)) {
                return command.getCommand();
            }
        }
        return PAGE_NOT_FOUND.getCommand();
    }
}
