package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Profile extends AppCompatActivity {
EditText name,city;
SharedPreferences s;
Button save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        save=findViewById(R.id.pbut);
        name=findViewById(R.id.pname);
        city=findViewById(R.id.pcity);
        s=getSharedPreferences("profile_weather",MODE_PRIVATE);
        name.setText(s.getString("pname",""));
        city.setText(s.getString("pcity",""));
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pname=name.getText().toString();
                String pcity=city.getText().toString();
                s.edit().putString("pname",pname).apply();
                s.edit().putString("pcity",pcity).apply();

            }
        });
    }
}