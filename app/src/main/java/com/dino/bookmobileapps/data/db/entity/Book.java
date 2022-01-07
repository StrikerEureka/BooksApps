package com.dino.bookmobileapps.data.db.entity;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


@Entity(tableName = "books")
public class Book implements Parcelable {

    @PrimaryKey
    @SerializedName("book_id")
    @ColumnInfo(name = "book_id")
    private int book_id;

    @Expose
    @SerializedName("book_name")
    @ColumnInfo(name = "book_name")
    private String book_name;

    @Expose
    @SerializedName("cover")
    @ColumnInfo(name = "cover")
    private String cover;

    @Expose
    @SerializedName("penulis")
    @ColumnInfo(name = "penulis")
    private String penulis;

    @Expose
    @SerializedName("penerbit")
    @ColumnInfo(name = "penerbit")
    private String penerbit;

    @Expose
    @SerializedName("sinopsis")
    @ColumnInfo(name = "sinopsis")
    private String sinopsis;

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getPenulis() {
        return penulis;
    }

    public void setPenulis(String penulis) {
        this.penulis = penulis;
    }

    public String getPenerbit() {
        return penerbit;
    }

    public void setPenerbit(String penerbit) {
        this.penerbit = penerbit;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public Book(int book_id, String book_name, String cover, String penulis, String penerbit, String sinopsis) {
        this.book_id = book_id;
        this.book_name = book_name;
        this.cover = cover;
        this.penulis = penulis;
        this.penerbit = penerbit;
        this.sinopsis = sinopsis;
    }

    protected Book(Parcel in) {
        book_id = in.readInt();
        book_name = in.readString();
        cover = in.readString();
        penulis = in.readString();
        penerbit = in.readString();
        sinopsis = in.readString();
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(book_id);
        dest.writeString(book_name);
        dest.writeString(cover);
        dest.writeString(penulis);
        dest.writeString(penerbit);
        dest.writeString(sinopsis);
    }
}
