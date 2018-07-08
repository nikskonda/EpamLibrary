package by.epam.java.training.web.command;

import java.util.Map;

public class CommandFactory {

    private static CommandFactory instance = new CommandFactory();

    private CommandManager commandManager = new CommandManager();


    private CommandFactory() {
    }

    public static Command getCommand(String command){
        return getInstance().commandManager.getCommand(command);
    }

    private static CommandFactory getInstance(){
        return instance;
    }

}
