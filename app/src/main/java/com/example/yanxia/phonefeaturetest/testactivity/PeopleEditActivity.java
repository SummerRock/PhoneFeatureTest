package com.example.yanxia.phonefeaturetest.testactivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.yanxia.phonefeaturetest.R;
import com.example.yanxia.phonefeaturetest.dataModel.People;
import com.example.yanxia.phonefeaturetest.utils.PeopleDataManager;
import com.google.gson.Gson;

public class PeopleEditActivity extends AppCompatActivity {

    private static final String TAG = "PeopleEdit_TAG";
    private String string;
    private Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people_edit);

        Button button1 = findViewById(R.id.people_button_1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                string = gson.toJson(PeopleDataManager.getInstance().getDefaultPeople());
            }
        });

        Button button2 = findViewById(R.id.people_button_2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                People people = gson.fromJson(string, People.class);
                Log.i(TAG, "");
            }
        });
    }
}
