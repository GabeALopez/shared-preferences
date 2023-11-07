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

        SharedPreferences mainPreference = getSharedPreferences("mainPreference", MODE_PRIVATE);

        int appMainCount = mainPreference.getInt("mainAppCount", 0);

        appMainCount++;

        SharedPreferences.Editor editor = mainPreference.edit();
        editor.putInt("mainAppCount", appMainCount);
        editor.apply();

        TextView txtView = findViewById(R.id.textView1);

        txtView.setText("App started " + appMainCount + " times");

        DatabaseHelper databaseHelper = new DatabaseHelper(this);

        Button saveBtn = findViewById(R.id.buttonSave);
        Button showBtn = findViewById(R.id.buttonShow);
        EditText cityEditText = findViewById(R.id.editTextCity);
        EditText tempEditText = findViewById(R.id.editTextTemp);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String city = cityEditText.getText().toString();
                String temperatureStr = tempEditText.getText().toString();

                if(!city.isEmpty() && !temperatureStr.isEmpty()){
                    int temperatureInt = Integer.parseInt(temperatureStr);
                    databaseHelper.storeData(new ModelClass(city, temperatureInt));

                    Toast.makeText(getApplicationContext(), "Data saved", Toast.LENGTH_SHORT).show();

                    cityEditText.setText("");
                    tempEditText.setText("");
                }
            }
        });

        showBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int secondActCount = mainPreference.getInt("secondActCount", 0);
                secondActCount++;
                SharedPreferences.Editor editor1 = mainPreference.edit();

                editor1.putInt("secondActCount", secondActCount);
                editor1.apply();

                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            }
        });

    }

    public void onStartActivityButtonClicked(View view) {
        String city = "";
        String temperature = "";
        EditText editTextCity = findViewById(R.id.editTextCity);
        city = editTextCity.getText().toString();
        EditText editTextTemp = findViewById(R.id.editTextCity);
        temperature = editTextTemp.getText().toString();
        if (city.equals("") || temperature.equals(""))
        {
            Toast.makeText(this, "First, enter the city and temperature!", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }

    public void onSaveButtonClicked(View view) {
        String city = "";
        String temperature = "";
        EditText editTextCity = findViewById(R.id.editTextCity);
        city = editTextCity.getText().toString();
        EditText editTextTemp = findViewById(R.id.editTextCity);
        temperature = editTextTemp.getText().toString();
        if (city.equals("") || temperature.equals(""))
        {
            Toast.makeText(this, "First, enter the city and temperature!", Toast.LENGTH_SHORT).show();
            return;
        }

    }

}