package com.example.pract;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.pract.databinding.ActivityEditBinding;

public class EditActivity extends AppCompatActivity {

    public static final String BOOK_ID = "bookId";
    public static final String BOOK_NAME = "bookName";
    public static final String UNIT_PRICE = "unitPrice";

    ActivityEditBinding activityEditBinding;
    EditActivityClickHandler clickHandler;

    Book book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);


        book = new Book();

        clickHandler = new EditActivityClickHandler(this);
        activityEditBinding = DataBindingUtil.setContentView(this, R.layout.activity_edit);
        activityEditBinding.setClickhandler(clickHandler);
        activityEditBinding.setBook(book);


        Intent intent = getIntent();

        if (intent.hasExtra(BOOK_ID)) {
            book.setBookName(intent.getStringExtra(BOOK_NAME));
            book.setUnitPrice(intent.getStringExtra(UNIT_PRICE));

            setTitle("Update book");

        } else {

            setTitle("Add a book");
        }



    }


    public class EditActivityClickHandler{



        Context context;

        public EditActivityClickHandler(Context context) {
            this.context = context;
        }

        public void submitted(View view) {

            if (book.getBookName() == null) {

                Toast.makeText(context, "Required", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent();
                intent.putExtra(BOOK_NAME, book.getBookName());
                intent.putExtra(UNIT_PRICE, book.getUnitPrice());
                setResult(RESULT_OK, intent);
                finish();
                Toast.makeText(context, "Submitted", Toast.LENGTH_SHORT).show();

            }

        }
    }





}