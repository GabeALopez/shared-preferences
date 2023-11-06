package mobileapp.course.edu.sqlproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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