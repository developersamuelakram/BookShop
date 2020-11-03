package com.example.pract;


import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class Repository {

    BookDao bookDao;
    CategoryDao categoryDao;


    public Repository(Application application) {

        BookDatabase bookDatabase = BookDatabase.getInstance(application);
        bookDao = bookDatabase.getBookDao();
        categoryDao = bookDatabase.getCategoryDao();

    }

    // methods for getting categories and books

    public LiveData<List<Category>> getAllCategory() {
        return categoryDao.getAllCategory();
    }


    // cuz we need books by id of their category
    public LiveData<List<Book>> getBooks(int categoryId) {
        return bookDao.getBooks(categoryId);
    }


    // create methods for updating, deleteing and adding books



    public void add(Book book) {

        new AddNewBoooks(bookDao).execute(book);

    }

    public static class AddNewBoooks extends AsyncTask<Book, Void, Void> {

        BookDao bookDao;

        public AddNewBoooks(BookDao bookDao) {
            this.bookDao = bookDao;
        }

        @Override
        protected Void doInBackground(Book... books) {

            bookDao.insertBook(books[0]);

            return null;
        }
    }


//update
    public void update(Book book) {

        new UpdateNewBooks(bookDao).execute(book);

    }

    public static class UpdateNewBooks extends AsyncTask<Book, Void, Void> {

        BookDao bookDao;

        public UpdateNewBooks(BookDao bookDao) {
            this.bookDao = bookDao;
        }

        @Override
        protected Void doInBackground(Book... books) {

            bookDao.updateBook(books[0]);

            return null;
        }
    }


    //deletebooks


    public void delBooks(Book book) {

        new DeleteBooks(bookDao).execute(book);

    }

    public static class DeleteBooks extends AsyncTask<Book, Void, Void> {

        BookDao bookDao;

        public DeleteBooks(BookDao bookDao) {
            this.bookDao = bookDao;
        }

        @Override
        protected Void doInBackground(Book... books) {

            bookDao.deleteBook(books[0]);

            return null;
        }
    }


}
