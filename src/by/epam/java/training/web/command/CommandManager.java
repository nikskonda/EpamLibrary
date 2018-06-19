package by.epam.java.training.web.command;

import by.epam.java.training.web.command.impl.l10n.Localization;
import by.epam.java.training.web.command.impl.user.SignIn;
import by.epam.java.training.web.command.impl.user.SignUp;

import java.util.HashMap;
import java.util.Map;

import static by.epam.java.training.web.command.Commandos.*;

public class CommandManager {

    private final Map<String, AbstractCommand> commands;

    public CommandManager() {
        this.commands = new HashMap<>();
        commands.put(SIGN_IN.getValue(), new SignIn());
        commands.put(SIGN_UP.getValue(), new SignUp());
        commands.put(RU.getValue(), new Localization());
        commands.put(EN.getValue(), new Localization());
    }

    public Command getCommand(String command){
        return this.commands.get(command);
    }

}
