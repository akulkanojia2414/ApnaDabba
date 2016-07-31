package example.akul.apnadabba;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;



import java.util.ArrayList;
import java.util.List;

/**
 * Created by akul on 12-07-2016.
 */
public class MyDbHelper extends SQLiteOpenHelper {
    // Database Info
    private static final String DATABASE_NAME = "MyDatabase";
    private static final int DATABASE_VERSION = 6;

    // Table Names
    private static final String TABLE_1 = "MyCart";

    // Cart Table Columns
    private static MyDbHelper sInstance;
    private String PRODUCT_DESCRIPTION = "PRODUCT_DESCRIPTION";
    private String PRODUCT_PRICE ="PRODUCT_PRICE";
    private String COUNTER = "COUNTER";
    private String PRODUCT_ID = "PRODUCT_ID";
    private String PRODUCT_NAME = "PRODUCT_NAME";
    private String TODAY = "TODAY";
    private String LIKES = "LIKES";
    private String IMAGE = "IMAGE";
    /**
     * Constructor should be private to prevent direct instantiation.
     * Make a call to the static method "getInstance()" instead.
     */
    private MyDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized MyDbHelper getInstance(Context context) {
        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (sInstance == null) {
            sInstance = new MyDbHelper(context.getApplicationContext());
        }
        return sInstance;
    }

    // Called when the database connection is being configured.
    // Configure database settings for things like foreign key support, write-ahead logging, etc.
    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    // Called when the database is created for the FIRST time.
    // If a database already exists on disk with the same DATABASE_NAME, this method will NOT be called.
    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_POSTS_TABLE = "CREATE TABLE " + TABLE_1 +
                " (" +
                PRODUCT_ID + " INTEGER PRIMARY KEY," +
                PRODUCT_NAME + " VARCHAR," +
                PRODUCT_DESCRIPTION + " VARCHAR," +
                PRODUCT_PRICE + " INTEGER," +
                COUNTER + " INTEGER," +
                TODAY + " INTEGER," +
                LIKES + " INTEGER," +
                IMAGE + " VARCHAR " +
                ");";


        db.execSQL(CREATE_POSTS_TABLE);

    }
    public void Delete()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_1);
    }

    public Cursor Search(int PRODUCT_ID)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_1 +" WHERE PRODUCT_ID="+PRODUCT_ID+";";
        Cursor c = db.rawQuery(sql, null);
        return c;
    }


    public void InsertPlease(DishesMenu data ) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PRODUCT_ID,data.product_id);
        values.put(PRODUCT_NAME,data.name);
        values.put(PRODUCT_DESCRIPTION,data.Description);
        values.put(PRODUCT_PRICE,data.price);
        values.put(COUNTER,0);
        values.put(TODAY,data.today);
        values.put(LIKES,data.likes);
        values.put(IMAGE,data.image);
        Cursor c = Search(data.product_id);
        if(c.getCount() == 0 )

           // Log.d("Akul","dl;skds");
            db.insert(TABLE_1,null,values);

        db.close();
        c.close();
    }

    public List<DishesMenu> getAllContacts() {
        List<DishesMenu> data = new ArrayList<DishesMenu>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_1;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                DishesMenu temp = new DishesMenu();
                temp.product_id = (Integer.parseInt(cursor.getString(0)));
                temp.name = (cursor.getString(1));
                temp.Description = (cursor.getString(2));
                temp.price = (Integer.parseInt(cursor.getString(3)));
                temp.counter = (Integer.parseInt(cursor.getString(6)));
                temp.today = Integer.parseInt(cursor.getString(7));
                temp.likes = Integer.parseInt(cursor.getString(8));
                // Adding contact to list
                data.add(temp);
            } while (cursor.moveToNext());
        }

        // return contact list
        return data;
    }

    public int update(int counter,DishesMenu data) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COUNTER,counter);


        // updating row
        return db.update(TABLE_1, values,  PRODUCT_ID + " = ?",
                new String[] { String.valueOf(data.product_id) });
    }


    // Called when the database needs to be upgraded.
    // This method will only be called if a database already exists on disk with the same DATABASE_NAME,
    // but the DATABASE_VERSION is different than the version of the database that exists on disk.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            // Simplest implementation is to drop all old tables and recreate them
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_1);
            onCreate(db);
        }
    }


}