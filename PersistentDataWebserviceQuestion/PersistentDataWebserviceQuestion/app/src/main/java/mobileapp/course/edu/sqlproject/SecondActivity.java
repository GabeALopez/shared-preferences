package mobileapp.course.edu.sqlproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        //Create an OnItemClickListener
        AdapterView.OnItemClickListener itemClickListener =
                new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> listView,
                                            View itemView,
                                            int position,
                                            long id) {
                        Toast.makeText(SecondActivity.this, "Item No: " + position, Toast.LENGTH_SHORT).show();
                    }
                };
        //Add the listener to the list view
        ListView listView = (ListView) findViewById(R.id.list_weather);
        listView.setOnItemClickListener(itemClickListener);

        String weather = getIntent().getStringExtra("Weather");
        String city = getIntent().getStringExtra("City");
    }

}