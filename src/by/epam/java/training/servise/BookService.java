package by.epam.java.training.servise;

import by.epam.java.training.model.book.Book;
import by.epam.java.training.model.user.ActiveUser;
import by.epam.java.training.model.user.AuthorizationForm;
import by.epam.java.training.model.user.RegistrationForm;
import by.epam.java.training.model.user.User;

import java.util.List;

public interface BookService {

    List<Book> getBooks(String locale);

}
