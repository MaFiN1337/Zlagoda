package com.example.controller;

import com.example.controller.command.Command;

class CommandFactory {

    private CommandFactory() {}

    static Command getCommand(String commandKey) {
        return CommandEnum.getCommand(commandKey);
    }
}