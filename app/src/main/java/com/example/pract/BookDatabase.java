package com.example.pract;


import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Category.class, Book.class}, version = 1)
public abstract  class BookDatabase extends RoomDatabase {


    public abstract BookDao getBookDao();
    public abstract  CategoryDao getCategoryDao();


    public static BookDatabase instance;

    public static synchronized BookDatabase getInstance(Context context) {

        if (instance == null) {

            instance = Room.databaseBuilder(context, BookDatabase.class, "shopDB").
                    fallbackToDestructiveMigration().addCallback(callback).
                    build();

        }

        return instance;

    }



    public static RoomDatabase.Callback callback = new RoomDatabase.Callback() {

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            new InitializeDatabase(instance).execute();
        }
    };


    public static class InitializeDatabase extends AsyncTask<Void, Void, Void> {

        BookDao bookDao;
        CategoryDao categoryDao;

        public InitializeDatabase(BookDatabase bookDatabase) {

            bookDao = bookDatabase.getBookDao();
            categoryDao = bookDatabase.getCategoryDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {



            Category category = new Category();
            category.setCategoryName("Text Books");
            category.setCategoryDescription("Text Books Description");


            Category category1 = new Category();
            category1.setCategoryName("Novels");
            category1.setCategoryDescription("Novels Description");



            Category category2 = new Category();
            category2.setCategoryName("Others Books");
            category2.setCategoryDescription("Text Books Decription");

            categoryDao.insertCategory(category);
            categoryDao.insertCategory(category1);
            categoryDao.insertCategory(category2);


            Book book = new Book();
            book.setBookName("Math for Beginner");
            book.setUnitPrice("$200");
            book.setCategoryId(1);

            Book book1 = new Book();
            book1.setBookName("Java for babies");
            book1.setUnitPrice("$300");
            book1.setCategoryId(1);



            Book book2 = new Book();
            book2.setBookName("Science for uncles");
            book2.setUnitPrice("$100");
            book2.setCategoryId(1);



            Book book3 = new Book();
            book3.setBookName("High School Magic");
            book3.setUnitPrice("$50");
            book3.setCategoryId(1);

            Book book5 = new Book();
            book5.setBookName("Java for Uncles");
            book5.setUnitPrice("$300");
            book5.setCategoryId(1);



            Book book4 = new Book();
            book4.setBookName("English for aunties");
            book4.setUnitPrice("$100");
            book4.setCategoryId(1);



            Book book6 = new Book();
            book6.setBookName("Harry Potter");
            book6.setUnitPrice("$200");
            book6.setCategoryId(2);

            Book book7 = new Book();
            book7.setBookName("LORDS OF THE RINGS");
            book7.setUnitPrice("$300");
            book7.setCategoryId(2);



            Book book8 = new Book();
            book8.setBookName("Sex and City");
            book8.setUnitPrice("$100");
            book8.setCategoryId(2);



            Book book9 = new Book();
            book9.setBookName("How to Train your dragon");
            book9.setUnitPrice("$50");
            book9.setCategoryId(2);

            Book book10 = new Book();
            book10.setBookName("Pirates of Carribean");
            book10.setUnitPrice("$300");
            book10.setCategoryId(2);



            Book book11 = new Book();
            book11.setBookName("Game of thrones");
            book11.setUnitPrice("$100");
            book11.setCategoryId(2);




            Book book12 = new Book();
            book12.setBookName("Make Money");
            book12.setUnitPrice("$200");
            book12.setCategoryId(3);

            Book book13 = new Book();
            book13.setBookName("Waste Money");
            book13.setUnitPrice("$300");
            book13.setCategoryId(3);



            Book book14 = new Book();
            book14.setBookName("Spend Money");
            book14.setUnitPrice("$100");
            book14.setCategoryId(3);



            Book book15 = new Book();
            book15.setBookName("Needs and Wants");
            book15.setUnitPrice("$50");
            book15.setCategoryId(3);

            Book book16 = new Book();
            book16.setBookName("Haves and Unknowns");
            book16.setUnitPrice("$300");
            book16.setCategoryId(3);



            Book book17 = new Book();
            book17.setBookName("Priacy");
            book17.setUnitPrice("$100");
            book17.setCategoryId(3);



            bookDao.insertBook(book1);
            bookDao.insertBook(book2);
            bookDao.insertBook(book3);
            bookDao.insertBook(book4);
            bookDao.insertBook(book5);
            bookDao.insertBook(book6);
            bookDao.insertBook(book7);
            bookDao.insertBook(book8);
            bookDao.insertBook(book9);
            bookDao.insertBook(book10);
            bookDao.insertBook(book11);
            bookDao.insertBook(book12);
            bookDao.insertBook(book13);
            bookDao.insertBook(book14);
            bookDao.insertBook(book15);
            bookDao.insertBook(book16);
            bookDao.insertBook(book17);



            return null;
        }

    }
}

