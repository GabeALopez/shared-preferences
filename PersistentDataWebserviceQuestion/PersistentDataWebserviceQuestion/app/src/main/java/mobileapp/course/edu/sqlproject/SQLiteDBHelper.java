package mobileapp.course.edu.sqlproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

public class SQLiteDBHelper extends SQLiteOpenHelper {

  Context context;
  private static final String dbName = "weatherDB.db";
  private static final int dbVer = 1;

  //Create first table
  String createTable = "CREATE TABLE weather ( " +
    "city TEXT, " +
    "temperature INTEGER)";

  //Set up database helper object
  public SQLiteDBHelper(Context context) {
    super(context, dbName, null, dbVer);
    this.context = context;
  }

  @Override
  //Create database
  public void onCreate(SQLiteDatabase database) {
    database.execSQL(createTable);
  }

  @Override
  //How to update information when necessary
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    // Drop older table if existed
    db.execSQL("DROP TABLE IF EXISTS " + dbName);
    onCreate(db); // Create tables again
  }

  //Inserts the weather information
  public void insertWeather(weatherClass weatherClass){
    SQLiteDatabase database = this.getWritableDatabase();

    //Get values of city and temperature
    ContentValues contentVal = new ContentValues();
    contentVal.put("city", weatherClass.getCity());
    contentVal.put("temperature", weatherClass.getTemperature());

    long checkQuery = database.insert("Weather", null, contentVal);

    //Check if the query had returned an error. If not show a message that the data was sent
    if(checkQuery != -1){
      Toast.makeText(context, "Data Saved", Toast.LENGTH_SHORT).show();

      database.close();
    }
    else{
      Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show();
    }

  }

  //Get the information of the weather and put into a ArrayList
  public ArrayList <String> getAllWeather() {
    ArrayList <String> weatherList = new ArrayList < > ();
    SQLiteDatabase database = this.getReadableDatabase();

    Cursor myCursor = database.rawQuery("SELECT city, temperature FROM weather", null);

    //Loop through all tables and push information into list
    if (myCursor.moveToFirst()) {
      do {
         String city = myCursor.getString(myCursor.getColumnIndex("city"));
         int temperature = myCursor.getInt(myCursor.getColumnIndex("temperature"));
         String cityTemperature = city + " - " + temperature;

         weatherList.add(cityTemperature);


      } while (myCursor.moveToNext());
    }
    // close database connection
    myCursor.close();
    database.close();
    // return the weather list
    return weatherList;
  }
}



