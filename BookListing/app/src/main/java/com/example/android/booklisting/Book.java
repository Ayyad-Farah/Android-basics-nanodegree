package com.example.android.booklisting;

import java.util.List;

/**
 * Created by ayyad on 4/6/2017.
 * <p>
 * this class represent book informations like title, authors, and published date.
 */

public class Book {

    /**
     * book title
     */
    String mTitle;

    /**
     * list of Authors of the book
     */
    List<String> mAuthors;

    /**
     * Published date of the book
     */
    String mPublishedDate;

    /**
     * Constructs a new {@link Book} object.
     *
     * @param title         is the title of the book.
     * @param authors       is the list contanied all the authors of the book.
     * @param publishedDate is the date that the book published in.
     */

    public Book(String title, List<String> authors, String publishedDate) {
        mTitle = title;
        mAuthors = authors;
        mPublishedDate = publishedDate;
    }

    /**
     * Returns the title of the book.
     */
    public String getTitle() {
        return mTitle;
    }

    /**
     * Returns the authors of the book.
     */
    public String getmAuthors() {
        String authors = "";
        for (String author : mAuthors) {
            authors += author + ", ";
        }
        return authors;
    }

    /**
     * Returns the published date of the book.
     */
    public String getPublishedDate() {
        return mPublishedDate;
    }
}
