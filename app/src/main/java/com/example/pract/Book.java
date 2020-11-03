package com.example.pract;


import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "books_table", foreignKeys = @ForeignKey(entity =
        Category.class, parentColumns = "id", childColumns = "categoryId", onDelete = CASCADE))
public class Book extends BaseObservable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "bookId")
    public int bookId;

    @ColumnInfo(name = "bookName")
    public String bookName;

    @ColumnInfo(name = "unitPrice")
    public String unitPrice;

    @ColumnInfo(name = "categoryId")
    public int categoryId;

    @Ignore
    public Book() {
    }

    public Book(int bookId, String bookName, String unitPrice, int categoryId) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.unitPrice = unitPrice;
        this.categoryId = categoryId;
    }


    @Bindable
    public int getBookId() {
        return bookId;
    }

    @Bindable
    public String getBookName() {
        return bookName;
    }

    @Bindable
    public String getUnitPrice() {
        return unitPrice;
    }


    @Bindable
    public int getCategoryId() {
        return categoryId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
        notifyPropertyChanged(BR.bookId);

    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
        notifyPropertyChanged(BR.bookName);

    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
        notifyPropertyChanged(BR.unitPrice);

    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
        notifyPropertyChanged(BR.categoryId);

    }
}
