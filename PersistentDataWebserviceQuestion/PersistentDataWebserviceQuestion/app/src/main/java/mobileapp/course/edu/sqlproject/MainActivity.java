package mobileapp.course.edu.sqlproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.SharedPreferences;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Set up shared preferences
        SharedPreferences mainPreference = getSharedPreferences("mainPreference", Context.MODE_PRIVATE);
        int mainCount = mainPreference.getInt("mainCount", 0);
        mainCount++;
        SharedPreferences.Editor editor = mainPreference.edit();
        editor.putInt("mainCount", mainCount);
        editor.apply();

        //User interface
        TextView txtView = findViewById(R.id.textView1);
        txtView.setText("App started " + mainCount + " times");

        //Set up database
        SQLiteDBHelper SQLiteDBHelper = new SQLiteDBHelper(this);

        //Set up elements
        Button saveBtn = findViewById(R.id.buttonSave);
        Button showBtn = findViewById(R.id.buttonShow);
        EditText cityEditText = findViewById(R.id.editTextCity);
        EditText tempEditText = findViewById(R.id.editTextTemp);

        //Save entries button
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cityStr = cityEditText.getText().toString();
                String temperatureStr = tempEditText.getText().toString();

                //Send information from editText to database
                if(!cityStr.isEmpty() && !temperatureStr.isEmpty()){
                    int temperatureInt = Integer.parseInt(temperatureStr);
                    SQLiteDBHelper.insertWeather(new weatherClass(cityStr, temperatureInt));

                    Toast.makeText(getApplicationContext(), "Data saved", Toast.LENGTH_SHORT).show();

                    //Reset text
                    cityEditText.setText("");
                    tempEditText.setText("");
                }
                else{
                    Toast.makeText(
                            getApplicationContext(),
                            "Insert information for city or temperature",
                            Toast.LENGTH_SHORT
                    ).show();
                }
            }
        });

        //Show information in the second activity
        showBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Set up shared preference for the second activity
                int secondActCount = mainPreference.getInt("secondActCount", 0);
                secondActCount++;
                SharedPreferences.Editor editor1 = mainPreference.edit();

                editor1.putInt("secondActCount", secondActCount);
                editor1.apply();

                //Show second activity
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("mainKey", 0);
                MainActivity.this.startActivity(intent);
            }
        });

    }



}