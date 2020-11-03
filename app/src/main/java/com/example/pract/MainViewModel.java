package com.example.pract;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class MainViewModel extends AndroidViewModel {


    Repository repository;
    LiveData<List<Category>> categories;
    LiveData<List<Book>> books;

    public MainViewModel(@NonNull Application application) {
        super(application);

        repository = new Repository(application);

    }


    LiveData<List<Book>> getBooks(int categoryId) {
        books = repository.getBooks(categoryId);
        return books;

    }

    LiveData<List<Category>> getCategories() {
        categories = repository.getAllCategory();
        return categories;

    }



    public void addBooks(Book book) {
        repository.add(book);
    }



    public void updateBooks(Book book) {
        repository.update(book);
    }


    public void deleteBooks(Book book) {
        repository.delBooks(book);
    }
}
