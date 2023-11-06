package mobileapp.course.edu.sqlproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

  Context context;
  String dbName = "weatherDB.db";
  int dbVer = 1;

  String createTable = "CREATE TABLE weather ( " +
    "city TEXT, " +
    "temperature INTEGER)";

  public DatabaseHelper(Context context) {
    super(context, dbName, null, dbVer);
    this.context = context;
  }

  @Override
  public void onCreate(SQLiteDatabase database) {
    database.execSQL(createTable);
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    // Drop older table if existed
    db.execSQL("DROP TABLE IF EXISTS " + dbName);
    onCreate(db); // Create tables again
  }

  public void storeData(ModelClass modelClass){
    SQLiteDatabase database = this.getWritableDatabase();

    ContentValues contentVal = new ContentValues();
    contentVal.put("city", modelClass.getCity());
    contentVal.put("temperature", modelClass.getTemperature());

    long checkQuery = database.insert("Weather", null, contentVal);

    if(checkQuery != -1){
      Toast.makeText(context, "Data Saved", Toast.LENGTH_SHORT).show();

      database.close();
    }
    else{
      Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show();
    }

  }

  public List < String > getAllWeather() {
    List < String > weatherList = new ArrayList < > ();
    SQLiteDatabase database = this.getReadableDatabase();

    Cursor myCursor = database.rawQuery("SELECT city, temperature, FROM weather", null);

    // looping through all rows and adding to list
    if (myCursor.moveToFirst()) {
      do {
         String city = myCursor.getString(myCursor.getColumnIndex("city"));
         int temperature = myCursor.getInt(myCursor.getColumnIndex("temperature"));
         String cityTemperature = city + " - " + temperature;

         weatherList.add(cityTemperature);


      } while (myCursor.moveToNext());
    }
    // close db connection
    myCursor.close();
    database.close();
    // return notes list
    return weatherList;
  }
}



