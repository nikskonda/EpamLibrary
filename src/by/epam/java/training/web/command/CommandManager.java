package by.epam.java.training.web.command;

import by.epam.java.training.web.command.impl.OpenErrorPage;
import by.epam.java.training.web.command.impl.admin.*;
import by.epam.java.training.web.command.impl.book.*;
import by.epam.java.training.web.command.impl.l10n.Localization;
import by.epam.java.training.web.command.impl.moder.*;
import by.epam.java.training.web.command.impl.news.ShowNews;
import by.epam.java.training.web.command.impl.news.ShowNewsList;
import by.epam.java.training.web.command.impl.user.*;

import java.util.HashMap;
import java.util.Map;

import static by.epam.java.training.web.command.CommandName.*;

public class CommandManager {

    private final Map<String, AbstractCommand> commands;

    public CommandManager() {
        this.commands = new HashMap<>();

        //admin
        commands.put(SHOW_USER_LIST, new ShowUserList());
        commands.put(SHOW_USER, new OpenUser());
        commands.put(CHANGE_USER_ROLE, new ChangeUserRole());
        commands.put(DELETE_USER, new DeleteUser());
        commands.put(FIND_USERS, new FindUserList());

        //moder
        commands.put(ADD_NEWS, new AddNews());
        commands.put(OPEN_EDITING_NEWS, new OpenEditingNews());
        commands.put(EDIT_NEWS, new EditNews());
        commands.put(DELETE_NEWS, new DeleteNews());

        commands.put(OPEN_ADD_BOOK, new OpenAddBook());
        commands.put(ADD_BOOK, new AddBook());
        commands.put(OPEN_EDITING_BOOK, new OpenEditingBook());
        commands.put(EDIT_BOOK, new EditBook());
        commands.put(DELETE_BOOK, new DeleteBook());

        //user
        commands.put(SIGN_IN, new SignIn());
        commands.put(SIGN_UP, new SignUp());
        commands.put(SIGN_OUT, new SignOut());
        commands.put(OPEN_PROFILE, new OpenProfile());
        commands.put(UPDATE_PROFILE, new UpdateProfile());

        //book
        commands.put(SHOW_BOOK_CATALOG, new ShowBookCatalog());
        commands.put(SHOW_BOOK, new ShowBook());
        commands.put(FIND_BOOKS, new FindBookList());
        commands.put(READ_BOOK, new ReadBook());

        commands.put(OPEN_BOOKMARK, new OpenBookmark());
        commands.put(SET_BOOKMARK, new SetBookmark());

        //news
        commands.put(SHOW_NEWS_LIST, new ShowNewsList());
        commands.put(SHOW_NEWS, new ShowNews());

        commands.put(RU, new Localization());
        commands.put(EN, new Localization());

        commands.put(ERROR, new OpenErrorPage());
    }

    public Command getCommand(String command){
        return this.commands.get(command);
    }

}
