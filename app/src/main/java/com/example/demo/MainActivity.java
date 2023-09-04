package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
TextView t;
String temp;
EditText e;
SharedPreferences s;

    private final String appid="&appid=f64e37d2a555f6722b91578018799050";
    private final String url="http://api.openweathermap.org/data/2.5/weather?q=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t=findViewById(R.id.click);
        s=getSharedPreferences("weatherf1",MODE_PRIVATE);

        e=findViewById(R.id.txt);
        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!e.getText().toString().equals("")) {
                    Intent i = new Intent(MainActivity.this, can.class);
                    i.putExtra("city",e.getText().toString());

                    temp=url+(e.getText().toString())+appid;
                    ;
                    JsonObjectRequest j=new JsonObjectRequest(Request.Method.GET, temp, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                            try {
                                Log.i("nerist",response.toString());

                                JSONObject main=response.getJSONObject("main");
                                JSONObject arr=response.getJSONArray("weather").getJSONObject(0);
                                JSONObject wind=response.getJSONObject("wind");
                                JSONObject sys=response.getJSONObject("sys");
                                s.edit().putString("temp",main.getString("temp")).apply();
                                s.edit().putString("max",main.getString("temp_max")).apply();
                                s.edit().putString("min",main.getString("temp_min")).apply();
                                s.edit().putString("desc",arr.getString("description")).apply();
                                s.edit().putString("visible",response.getString("visibility")).apply();
                                s.edit().putString("wspeed",wind.getString("speed")).apply();
                                s.edit().putString("country",sys.getString("country")).apply();
                                s.edit().putString("feel",main.getString("feels_like")).apply();
                                s.edit().putString("humidity",main.getString("humidity")).apply();
                                s.edit().putString("pressure",main.getString("pressure")).apply();
                            //   Toast.makeText(getApplicationContext(),"scroll down jui!",Toast.LENGTH_LONG).show();
                                startActivity(i);
                            }
                            catch (Exception e){
                                Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_SHORT).show();
                            }

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getApplicationContext(),"wrong input!",Toast.LENGTH_LONG).show();
                        }
                    });
                    RequestQueue q=Volley.newRequestQueue(MainActivity.this);

                    q.add(j);


                }
                else{

                    Toast.makeText(getApplicationContext(),"Empty input",Toast.LENGTH_LONG).show();
                }
    }
});
    }}