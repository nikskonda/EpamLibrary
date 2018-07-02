package by.epam.java.training.web.command;

import java.util.Map;

public class CommandFactory {

    private static CommandFactory instance = new CommandFactory();

    private CommandManager commandManager = new CommandManager();


    private CommandFactory() {
    }

    public Command getCommand(String command){
        return instance.commandManager.getCommand(command);
    }

    public static CommandFactory getInstance(){
        return instance;
    }

    public CommandManager getCommandManager(){
        return this.commandManager;
    }
}
