package com.example.demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.res.ResourcesCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
TextView t;
String temp;
Intent i;
EditText e;
SharedPreferences s;

    private final String appid="&appid=f64e37d2a555f6722b91578018799050";
    private final String url="http://api.openweathermap.org/data/2.5/weather?q=";
    private static final String channel="channel 1";
    private static final int NOTIFICATION_ID=100;
private NotificationManager notificationManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t=findViewById(R.id.click);
        s=getSharedPreferences("weatherf1",MODE_PRIVATE);
        e=findViewById(R.id.txt);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
notificationManager= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        Notification n;
        s=getSharedPreferences("weatherf1",MODE_PRIVATE);
        String cit=s.getString("city","");
if(cit!="") {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        n = new Notification.Builder(this)
                .setSmallIcon(R.drawable.ico)
                .setContentText(getdata(cit))
                .setSubText(cit)
                .setChannelId(channel)
                .build();
        notificationManager.createNotificationChannel(new NotificationChannel(channel, "channel 1", NotificationManager.IMPORTANCE_HIGH));

    } else {
        n = new Notification.Builder(this)
                .setSmallIcon(R.drawable.ico)
                .setContentText(getdata(cit))
                .setSubText(cit)

                .build();

    }
    notificationManager.notify(NOTIFICATION_ID, n);

}


        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!e.getText().toString().equals("")) {
                    i = new Intent(MainActivity.this, can.class);
                    i.putExtra("city", e.getText().toString());

                    String city=e.getText().toString();
                    getdata(city);
                    startActivity(i);



                }

                else{

                    Toast.makeText(getApplicationContext(),"Empty input",Toast.LENGTH_LONG).show();
                }

            }});





}

public String getdata(String city){

    temp = url + city + appid;
    JsonObjectRequest j=new JsonObjectRequest(Request.Method.GET, temp, null, new Response.Listener<JSONObject>() {
        @Override
        public void onResponse(JSONObject response) {

            try {
                Log.i("nerist",response.toString());

                JSONObject main=response.getJSONObject("main");
                JSONObject arr=response.getJSONArray("weather").getJSONObject(0);
                JSONObject wind=response.getJSONObject("wind");
                JSONObject sys=response.getJSONObject("sys");

                s.edit().putString("city",city).apply();
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
    s=getSharedPreferences("weatherf1",MODE_PRIVATE);
    int mx=(int)((Float.parseFloat(s.getString("max","")))-273.15);
    int mn=(int)((Float.parseFloat(s.getString("min","")))-273.15);
    int fk=(int)((Float.parseFloat(s.getString("feel","")))-273.15);
return "max temp : "+mx+" ,  min temp : "+mn+" , feels like : "+fk;
}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.men1,menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==R.id.about){
            Intent i=new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/Jugalcody"));
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }
}