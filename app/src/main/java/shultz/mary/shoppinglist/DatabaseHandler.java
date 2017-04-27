package shultz.mary.shoppinglist;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;


/**
 * Created by Mary on 4/27/2017.
 */

public class DatabaseHandler {
    public final static String DB_NAME = "List";
    public final String TABLE_NAME = "items";
    private final String ITEM_COL = "item";
    private final String CREATE_COL = "dateCreated";
    private final String COMPLETE_COL = "dateCompleted";
    private SQLiteDatabase listDatabase;
    private Cursor cursor;
    private int item, createdDate, completedDate;


    public DatabaseHandler(SQLiteDatabase listDatabase) {
        this.setListDatabase(listDatabase);
        initializeItemTable();
    }

    private void initializeItemTable() {
        listDatabase.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "("
                + ITEM_COL + " varchar, "
                + CREATE_COL + " datetime," + COMPLETE_COL + " datetime)");

    }


    public void setListDatabase(SQLiteDatabase listDatabase) {
        this.listDatabase = listDatabase;
    }

    public ArrayList<ShoppingItem> getAllItems() {
        ArrayList<ShoppingItem> list = new ArrayList<>();

        cursor = listDatabase.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        item = cursor.getColumnIndex(ITEM_COL);
        createdDate = cursor.getColumnIndex(CREATE_COL);
        completedDate = cursor.getColumnIndex(COMPLETE_COL);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                list.add(new ShoppingItem(cursor.getString(item), cursor.getString(createdDate), cursor.getString(completedDate)));
            } while (cursor.moveToNext());
        }
        return list;
    }

    public void addItem(ShoppingItem item) {
        ContentValues itemValues = new ContentValues();
        itemValues.put(ITEM_COL, item.getItemName());
        itemValues.put(CREATE_COL, item.getDateCreated());
        itemValues.put(COMPLETE_COL, item.getDateCompleted());
        listDatabase.insert(TABLE_NAME, null, itemValues);

    }
}
