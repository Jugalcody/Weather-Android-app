package com.example.demo;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class can extends AppCompatActivity {
    EditText e;
LinearLayout p;
    LinearLayout ll;
    String city,temp;
    TextView t1,t2,t3,t4,t5,t6,t7,t8,t9;
    SharedPreferences s;
    LottieAnimationView l1,l2,l3,l4;
    Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_can);
        city=getIntent().getStringExtra("city");
        p=findViewById(R.id.parent);
        t1=findViewById(R.id.place);
        l1=findViewById(R.id.skyl);
        l2=findViewById(R.id.cloudl);
        l3=findViewById(R.id.rainl);
        l4=findViewById(R.id.thundarl);
        s=getSharedPreferences("weatherf1",MODE_PRIVATE);
b=findViewById(R.id.show);
        t2=findViewById(R.id.temp);
        t3=findViewById(R.id.desc);
        t4=findViewById(R.id.max);
        t5=findViewById(R.id.min);
        t6=findViewById(R.id.wspeed);
        t7=findViewById(R.id.visible);
        t8=findViewById(R.id.humidity);
        t9=findViewById(R.id.pressure);
getSupportActionBar().hide();
  
                if(s.getString("desc","").equals("clear sky")){
                    l1.setVisibility(View.VISIBLE);
                    p.setBackground(getResources().getDrawable(R.drawable.cloud2));
                }
                else if(s.getString("desc","").equals("broken clouds") || s.getString("desc","").equals("overcast clouds") || s.getString("desc","").equals("broken clouds")){
                    l2.setVisibility(View.VISIBLE);
                   // p.setBackground(getResources().getDrawable(R.drawable.));
                }
                else if(s.getString("desc","").equals("rain")){
                    l3.setVisibility(View.VISIBLE);
                     p.setBackground(getResources().getDrawable(R.drawable.lake));
                }

                else if(s.getString("desc","").equals("thunderstorm") || s.getString("desc","").equals("thunderstorm with light rain")){
                    l4.setVisibility(View.VISIBLE);
                   // p.setBackground(getResources().getDrawable(R.drawable.lake));
                }

                t1.setText(city+","+s.getString("country",""));
                t2.setVisibility(View.VISIBLE);
                t4.setVisibility(View.VISIBLE);
                String tem=s.getString("temp","");
                int a=(int)((Float.parseFloat(tem))-273.15);
                int mx=(int)((Float.parseFloat(s.getString("max","")))-273.15);
                int mn=(int)((Float.parseFloat(s.getString("min","")))-273.15);
                int fk=(int)((Float.parseFloat(s.getString("feel","")))-273.15);
                t2.setText(String.valueOf(a)+"\u00B0");
                t4.setText(s.getString("desc",""));
                t3.setText("max temp :   "+mx+"\u00B0C        min temp : "+mn+"\u00B0C");
                t5.setText("feels like :   "+fk+"\u00B0"+" C");
                t6.setText("wind speed : "+s.getString("wspeed","")+" km/hr");
                t7.setText("visibility : "+s.getString("visible",""));
                t8.setText("humidity : "+s.getString("humidity","")+" %");
                t9.setText("pressure : "+s.getString("pressure",""));
                b.setVisibility(View.GONE);


    }}