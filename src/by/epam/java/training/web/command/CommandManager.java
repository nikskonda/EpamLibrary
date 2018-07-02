package by.epam.java.training.web.command;

import by.epam.java.training.web.command.impl.*;
import by.epam.java.training.web.command.impl.l10n.Localization;
import by.epam.java.training.web.command.impl.user.OpenProfile;
import by.epam.java.training.web.command.impl.user.SignIn;
import by.epam.java.training.web.command.impl.user.SignUp;
import by.epam.java.training.web.command.impl.user.UpdateProfile;

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
        commands.put(OPEN_CATALOG.getValue(), new BookCatalog());
        commands.put(OPEN_NEWS.getValue(), new ShowNewsList());
        commands.put(ADD_NEWS.getValue(), new NewsConstructor());
        commands.put(OPEN_PROFILE.getValue(), new OpenProfile());
        commands.put(UPDATE_PROFILE.getValue(), new UpdateProfile());
        commands.put(SHOW_NEWS_BY_ID.getValue(), new ShowNews());
        commands.put(SHOW_USER_LIST.getValue(), new ShowUserList());
        commands.put(SHOW_BOOK_BY_ID.getValue(), new ShowBook());
        commands.put(READ_BOOK_BY_ID.getValue(), new ReadBook());
    }

    public Command getCommand(String command){
        return this.commands.get(command);
    }

}
