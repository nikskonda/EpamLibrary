package by.epam.java.training.dao;

import by.epam.java.training.model.book.Book;
import by.epam.java.training.model.book.BookCover;
import by.epam.java.training.model.book.Bookmark;

import java.util.List;

public interface BookmarkDAO {

    Integer getBookmark(Bookmark bookmark);

    boolean setBookmark(Bookmark bookmark);
}
