package com.dino.bookmobileapps.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.File;


public class BookRequest implements Parcelable {

    @Expose
    @SerializedName("book_id")
    private int book_id;

    @Expose
    @SerializedName("book_name")
    private String book_name;

    @Expose
    @SerializedName("cover")
    private String cover;

    @Expose
    @SerializedName("penulis")
    private String penulis;

    @Expose
    @SerializedName("penerbit")
    private String penerbit;

    @Expose
    @SerializedName("sinopsis")
    private String sinopsis;

    @Expose
    @SerializedName("file")
    private File file;

    protected BookRequest(Parcel in) {
        book_id = in.readInt();
        book_name = in.readString();
        cover = in.readString();
        penulis = in.readString();
        penerbit = in.readString();
        sinopsis = in.readString();
    }

    public static final Creator<BookRequest> CREATOR = new Creator<BookRequest>() {
        @Override
        public BookRequest createFromParcel(Parcel in) {
            return new BookRequest(in);
        }

        @Override
        public BookRequest[] newArray(int size) {
            return new BookRequest[size];
        }
    };

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

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public BookRequest(int book_id, String book_name, String cover, String penulis, String penerbit, String sinopsis) {
        this.book_id = book_id;
        this.book_name = book_name;
        this.cover = cover;
        this.penulis = penulis;
        this.penerbit = penerbit;
        this.sinopsis = sinopsis;
    }

    public BookRequest(int book_id, String book_name, String penulis, String penerbit, String sinopsis, File file) {
        this.book_id = book_id;
        this.book_name = book_name;
        this.penulis = penulis;
        this.penerbit = penerbit;
        this.sinopsis = sinopsis;
        this.file = file;
    }

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
