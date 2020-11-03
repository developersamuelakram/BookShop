package com.example.pract;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pract.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    public static final int UPDATE_BOOKS = 100;
    public static final int NEWBOOOKS = 200;

    MainActivityHandler handler;
    ActivityMainBinding activityMainBinding;
    MainViewModel viewModel;
    ArrayList<Category> categoryArrayList;
    Category selectedCategory;
    Book book;
    ArrayList<Book> bookArrayList;
    RecyclerView recyclerView;

    BooksAdapter mAdapter;
    int selectedBookId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        handler = new MainActivityHandler(this);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        activityMainBinding.setClickhandler(handler);

        viewModel.getCategories().observe(this, new Observer<List<Category>>() {
            @Override
            public void onChanged(List<Category> categories) {
                categoryArrayList = (ArrayList<Category>) categories;

                for (Category category: categories) {


                    Log.i("mytag", categories.toString());

                }

                showSpinnner();
            }
        });

    }

    private void showSpinnner() {
        ArrayAdapter<Category> categoryArrayAdapter = new ArrayAdapter<>(this, R.layout.spinneritem, categoryArrayList);
        categoryArrayAdapter.setDropDownViewResource(R.layout.spinneritem);
        activityMainBinding.setSpinnerAdapter(categoryArrayAdapter);
    }


    public void loadbookarray(int categoryid) {

        viewModel.getBooks(categoryid).observe(this, new Observer<List<Book>>() {
            @Override
            public void onChanged(List<Book> books) {

                bookArrayList = (ArrayList<Book>) books;
                loadRecyclerview();
            }
        });




    }

    private void loadRecyclerview() {

        recyclerView  = activityMainBinding.secondarylayout.rvBooks;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        mAdapter = new BooksAdapter();
        recyclerView.setAdapter(mAdapter);
        mAdapter.setBooks(bookArrayList);


        // attaching a listener

        mAdapter.setListener(new BooksAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Book book) {

                selectedBookId = book.getBookId();

                Intent intent = new Intent (MainActivity.this, EditActivity.class);
                intent.putExtra(EditActivity.BOOK_NAME, book.getBookName());
                intent.putExtra(EditActivity.UNIT_PRICE, book.getUnitPrice());
                intent.putExtra(EditActivity.BOOK_ID, selectedBookId);
                startActivityForResult(intent, UPDATE_BOOKS);

            }
        });


        // for swipe and delete

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                    Book book = bookArrayList.get(viewHolder.getAdapterPosition());
                    viewModel.deleteBooks(book);

            }
        }).attachToRecyclerView(recyclerView);


    }


    public class MainActivityHandler{


        Context context;

        public MainActivityHandler(Context context) {
            this.context = context;
        }

        public void OnSelectedItem(AdapterView<?> parent, View view, int pos, long id) {

            selectedCategory = (Category) parent.getItemAtPosition(pos);


            loadbookarray(selectedCategory.getId());


        }


        public void onfabClicked(View view) {

            book = new Book();

            Intent intent = new Intent (MainActivity.this, EditActivity.class);
            intent.putExtra(EditActivity.BOOK_NAME, book.getBookName());
            intent.putExtra(EditActivity.UNIT_PRICE, book.getUnitPrice());
            startActivityForResult(intent, NEWBOOOKS);


        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        int selectedCategoryId = selectedCategory.getId();


        if (requestCode == UPDATE_BOOKS && resultCode == RESULT_OK) {

                Book book = new Book();
                book.setBookName(data.getStringExtra(EditActivity.BOOK_NAME));
                book.setUnitPrice(data.getStringExtra(EditActivity.UNIT_PRICE));
                book.setBookId(selectedBookId);
                book.setCategoryId(selectedCategoryId);
                viewModel.updateBooks(book);



        } else if (requestCode == NEWBOOOKS && resultCode == RESULT_OK) {


            Book book = new Book();
            book.setBookName(data.getStringExtra(EditActivity.BOOK_NAME));
            book.setUnitPrice(data.getStringExtra(EditActivity.UNIT_PRICE));
            book.setCategoryId(selectedCategoryId);
            viewModel.addBooks(book);

        }
    }
}