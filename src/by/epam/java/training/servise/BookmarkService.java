package by.epam.java.training.servise;

import by.epam.java.training.model.book.Book;
import by.epam.java.training.model.book.BookCover;
import by.epam.java.training.model.book.Bookmark;

import java.util.List;

public interface BookmarkService {

    Integer getBookmark(Bookmark bookmark);

    boolean setBookmark(Bookmark bookmark);
}

