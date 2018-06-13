package by.epam.java.training.web.command;

import by.epam.java.training.web.command.impl.l10n.Localization;
import by.epam.java.training.web.command.impl.sign.OpenSignIn;
import by.epam.java.training.web.command.impl.sign.OpenSignUp;
import by.epam.java.training.web.command.impl.sign.SignIn;
import by.epam.java.training.web.command.impl.sign.SignUp;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {

    private static CommandFactory instance = new CommandFactory();

    private Map<String, Command> commands;

    public CommandFactory() {
        this.commands = new HashMap<>();
        commands.put(Commands.SIGN_IN.toString().toLowerCase(), new SignIn());
        commands.put(Commands.SIGN_UP.toString().toLowerCase(), new SignUp());
        commands.put(Commands.OPEN_SIGN_IN.toString().toLowerCase(), new OpenSignIn());
        commands.put(Commands.OPEN_SIGN_UP.toString().toLowerCase(), new OpenSignUp());
        commands.put(Commands.RU.toString().toLowerCase(), new Localization());
        commands.put(Commands.EN.toString().toLowerCase(), new Localization());
    }

    public Command getCommand(String command){
        return this.commands.get(command);
    }

    public static CommandFactory getInstance(){
        return instance;
    }
}
