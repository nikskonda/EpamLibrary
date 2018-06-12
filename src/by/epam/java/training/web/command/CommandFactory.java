package by.epam.java.training.web.command;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private Map<Commands, Command> commands;

    public CommandFactory() {
        this.commands = new HashMap<>();
        commands.put(Commands.SIGN_IN, new );
        commands.put(Commands.SIGN_IN, );
    }

    public Command getCommand(Commands command){
        return this.commands.get(command);
    }
}
