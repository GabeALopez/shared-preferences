package mobileapp.course.edu.sqlproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        //Get intent from main activity
        Intent intent = getIntent();
        String value = intent.getStringExtra("mainKey");


        //Get the shared preference and assign values
        SharedPreferences sharedPreferences = getSharedPreferences("mainPreference", Context.MODE_PRIVATE);
        TextView textView = findViewById(R.id.textView1);
        int secondActCount = sharedPreferences.getInt("secondActCount", 0);
        textView.setText("Second activity shown: " + secondActCount + " times");

        //Set up database, get information from database, and push that information to the ArrayList
        SQLiteDBHelper dbHelp = new SQLiteDBHelper(this);
        ListView lstViewWeather = findViewById(R.id.list_weather);
        ArrayList<String> weatherLst = dbHelp.getAllWeather();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, weatherLst);
        lstViewWeather.setAdapter(adapter);


    }

}