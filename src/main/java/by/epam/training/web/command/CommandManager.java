package by.epam.training.web.command;

import by.epam.training.web.command.impl.GoToErrorPage;
import by.epam.training.web.command.impl.bookmark.DeleteBookmark;
import by.epam.training.web.command.impl.bookmark.GoToBookmark;
import by.epam.training.web.command.impl.bookmark.SetBookmark;
import by.epam.training.web.command.impl.bookmark.TakeListOfBookmarks;
import by.epam.training.web.command.impl.l10n.Localization;
import by.epam.training.web.command.impl.moder.add.AddBook;
import by.epam.training.web.command.impl.moder.add.AddNews;
import by.epam.training.web.command.impl.moder.add.GoToAddBookForm;
import by.epam.training.web.command.impl.moder.add.GoToAddNewsForm;
import by.epam.training.web.command.impl.moder.edit.EditBook;
import by.epam.training.web.command.impl.moder.edit.EditNews;
import by.epam.training.web.command.impl.moder.edit.GoToEditBookForm;
import by.epam.training.web.command.impl.moder.edit.GoToEditNewsForm;
import by.epam.training.web.command.impl.news.TakeNews;
import by.epam.training.web.command.impl.news.TakeListOfNews;
import by.epam.training.web.command.impl.admin.*;
import by.epam.training.web.command.impl.book.FindListOfBooks;
import by.epam.training.web.command.impl.book.ReadBook;
import by.epam.training.web.command.impl.book.TakeBook;
import by.epam.training.web.command.impl.book.TakeBookCatalog;
import by.epam.training.web.command.impl.moder.DeleteBook;
import by.epam.training.web.command.impl.moder.DeleteNews;
import by.epam.training.web.command.impl.user.*;

import java.util.HashMap;
import java.util.Map;

import static by.epam.training.web.command.CommandConstants.*;

/**
 * The class is used to store commands {@link Command} by name.
 *
 * @author Nikita Shkonda
 */
public class CommandManager {

    private final Map<String, AbstractCommand> commands;

    /**
     * Constructor of the {@link CommandManager}.
     *
     */
    public CommandManager() {
        this.commands = new HashMap<>();

        //admin
        commands.put(TAKE_LIST_OF_USERS, new TakeListOfUsers());
        commands.put(TAKE_USER, new TakeUser());
        commands.put(CHANGE_USER_ROLE, new ChangeUserRole());
        commands.put(DELETE_USER, new DeleteUser());
        commands.put(FIND_LIST_OF_USERS, new FindListOfUsers());

        //moder
        commands.put(GO_TO_ADD_NEWS_FORM, new GoToAddNewsForm());
        commands.put(ADD_NEWS, new AddNews());
        commands.put(GO_TO_EDIT_NEWS_FORM, new GoToEditNewsForm());
        commands.put(EDIT_NEWS, new EditNews());
        commands.put(DELETE_NEWS, new DeleteNews());

        commands.put(GO_TO_ADD_BOOK_FORM, new GoToAddBookForm());
        commands.put(ADD_BOOK, new AddBook());
        commands.put(GO_TO_EDIT_BOOK_FORM, new GoToEditBookForm());
        commands.put(EDIT_BOOK, new EditBook());
        commands.put(DELETE_BOOK, new DeleteBook());

        //user
        commands.put(SIGN_IN, new SignIn());
        commands.put(SIGN_UP, new SignUp());
        commands.put(SIGN_OUT, new SignOut());
        commands.put(GO_TO_PROFILE_FORM, new GoToProfileForm());
        commands.put(UPDATE_PROFILE, new UpdateProfile());

        //book
        commands.put(TAKE_BOOK_CATALOG, new TakeBookCatalog());
        commands.put(TAKE_BOOK, new TakeBook());
        commands.put(FIND_BOOKS, new FindListOfBooks());
        commands.put(READ_BOOK, new ReadBook());

        commands.put(GO_TO_BOOKMARK, new GoToBookmark());
        commands.put(SET_BOOKMARK, new SetBookmark());
        commands.put(TAKE_LIST_OF_BOOKMARKS, new TakeListOfBookmarks());
        commands.put(DELETE_BOOKMARK, new DeleteBookmark());

        //news
        commands.put(TAKE_LIST_OF_NEWS, new TakeListOfNews());
        commands.put(TAKE_NEWS, new TakeNews());

        commands.put(RU, new Localization());
        commands.put(EN, new Localization());

        commands.put(ERROR, new GoToErrorPage());
    }

    /**
     * Return {@link Command} by its corresponding command name.
     *
     * @param command command name.
     * @return {@link Command}
     */
    public Command getCommand(String command){
        return this.commands.get(command);
    }

}
