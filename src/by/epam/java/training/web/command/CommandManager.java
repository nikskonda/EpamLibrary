package by.epam.java.training.web.command;

import by.epam.java.training.web.command.impl.*;
import by.epam.java.training.web.command.impl.l10n.Localization;
import by.epam.java.training.web.command.impl.user.*;

import java.util.HashMap;
import java.util.Map;

import static by.epam.java.training.web.command.Commandos.*;

public class CommandManager {

    private final Map<String, AbstractCommand> commands;

    public CommandManager() {
        this.commands = new HashMap<>();
        commands.put(SIGN_IN, new SignIn());
        commands.put(SIGN_UP, new SignUp());
        commands.put(RU, new Localization());
        commands.put(EN, new Localization());
        commands.put(OPEN_CATALOG, new BookCatalog());
        commands.put(OPEN_NEWS, new ShowNewsList());
        commands.put(ADD_NEWS, new NewsConstructor());
        commands.put(OPEN_PROFILE, new OpenProfile());
        commands.put(UPDATE_PROFILE, new UpdateProfile());
        commands.put(SHOW_NEWS, new ShowNews());
        commands.put(SHOW_USER_LIST, new ShowUserList());
        commands.put(SHOW_BOOK, new ShowBook());
        commands.put(READ_BOOK, new ReadBook());
        commands.put(OPEN_USER, new OpenUser());
        commands.put(CHANGE_USER_ROLE, new ChangeUserRole());
        commands.put(DELETE_USER, new DeleteUser());
        commands.put(BOOK_SEARCH, new BookSearch());
    }

    public Command getCommand(String command){
        return this.commands.get(command);
    }

}
