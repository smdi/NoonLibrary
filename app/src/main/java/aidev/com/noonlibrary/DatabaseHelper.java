package aidev.com.noonlibrary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "BookDb";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Store.CREATE_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + Store.TABLE_NAME);
        onCreate(db);
    }

    public void insertBook(int id,int image, String name, int availability, String sem) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Store.ID, id);
        values.put(Store.IMAGE, image);
        values.put(Store.NAME, name);
        values.put(Store.AVAILABILITY, availability);
        values.put(Store.SEM, sem);
        db.insert(Store.TABLE_NAME, null, values);
        db.close();

    }

    public ArrayList<Store> getAllNotes() {
        ArrayList<Store> books = new ArrayList<>();


        String selectQuery = "SELECT  * FROM " + Store.TABLE_NAME ;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);


        if (cursor.moveToFirst()) {
            do {
                Store note = new Store();
                note.setBookId(cursor.getInt(cursor.getColumnIndex(Store.ID)));
                note.setAvailability(cursor.getInt(cursor.getColumnIndex(Store.AVAILABILITY)));
                note.setImage(cursor.getInt(cursor.getColumnIndex(Store.IMAGE)));
                note.setBookName(cursor.getString(cursor.getColumnIndex(Store.NAME)));
                note.setSem(cursor.getString(cursor.getColumnIndex(Store.SEM)));

                books.add(note);
            } while (cursor.moveToNext());
        }


        db.close();

        return books;
    }

    public int updateNote(int id,int avail) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Store.AVAILABILITY, avail);

        return db.update(Store.TABLE_NAME, values, Store.ID + " = ?",
                new String[]{String.valueOf(id)});
    }

    public int getBookAvailability(int bid) {

        SQLiteDatabase db = this.getWritableDatabase();
        int avail;
        String query = "SELECT * FROM Books WHERE bookid=" + bid;

        Cursor  cursor = db.rawQuery(query,null);

        if (cursor != null) {
            cursor.moveToFirst();
        }


       avail =  cursor.getInt(cursor.getColumnIndex(Store.AVAILABILITY));

       return avail;
    }


}