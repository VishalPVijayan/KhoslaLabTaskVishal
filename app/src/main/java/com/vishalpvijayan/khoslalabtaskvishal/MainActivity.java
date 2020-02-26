package com.vishalpvijayan.khoslalabtaskvishal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ajts.androidmads.fontutils.FontUtils;

import java.text.DecimalFormat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {



    public static String BaseUrl = "http://api.openweathermap.org/";
    public static String AppId = "5b030c3a3494c635e695d0c040569ae9";
    public static String lat = "12.97";
    public static String lon = "77.59";

     TextView weatherData;
     TextView textView1,textView2,textView3,textView4,textView5,textView6;
     ImageView img;
     RecyclerView recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//METHOD TO UPDATE API
        getCurrentData();

//METHOD TO UPDATE RECYCLERVIEW
       updateRecyclerData();

        recycler = findViewById(R.id.recycler);
        img = findViewById(R.id.img);
        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);
        textView4 = findViewById(R.id.textView4);
        textView5 = findViewById(R.id.textView5);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCurrentData();
            }
        });
    }

    private void updateRecyclerData() {
        PojoforRecycler[] myListData = new PojoforRecycler[]{
                new PojoforRecycler("27℃","27-02-2020", R.drawable.iconsunny),
                new PojoforRecycler("25℃","28-02-2020", R.drawable.windy),
                new PojoforRecycler("34℃","29-02-2020", R.drawable.iconsunny),
                new PojoforRecycler("23℃","01-03-2020", R.drawable.stormy),
                new PojoforRecycler("25℃","02-03-2020", R.drawable.windy),
                new PojoforRecycler("28℃","03-03-2020", R.drawable.windy),
                new PojoforRecycler("29℃","04-03-2020", R.drawable.iconsunny),
        };


        recycler = (RecyclerView) findViewById(R.id.recycler);
        ListAdapter adapter = new ListAdapter(myListData);
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, true));
        recycler.setAdapter(adapter);
    }

    void getCurrentData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        WeatherService service = retrofit.create(WeatherService.class);
        Call<WeatherResponse> call = service.getCurrentWeatherData(lat, lon, AppId);
        call.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(@NonNull Call<WeatherResponse> call, @NonNull Response<WeatherResponse> response) {
                if (response.code() == 200) {
                    WeatherResponse weatherResponse = response.body();

                    assert weatherResponse != null;

                    String countryname = "City: " + weatherResponse.name + ","+ weatherResponse.sys.country;


                    //CONVERTING TEMPERATURE
                    double a =  weatherResponse.main.temp ;
                    double b = a-273.15;


                    //CONVERTING MIN TEMPERATURE
                    double xmin = weatherResponse.main.temp_min;
                    double zmin = xmin - 273.15;

                    //CONVERTING MAX TEMPERATURE
                    double xmax = weatherResponse.main.temp_max;
                    double zmax = xmax - 273.15;

                    String wind = "Wind Speed:  " + weatherResponse.wind.speed + "KM/H" ;



                    DecimalFormat precision = new DecimalFormat("0.00");
                    String r = String.valueOf(b);
                    String min = String.valueOf(zmin);
                    String max = String.valueOf(zmax);


                    String Max = "Max:  " + weatherResponse.main.temp_max ;



                   if(xmax <= 295){

                       img.setImageResource(R.drawable.windy);

                   }else{
                       img.setImageResource(R.drawable.iconsunny);
                   }





                    textView1.setText(countryname);
                    textView3.setText( r);
                    textView2.setText(wind);

                    textView4.setText(max);
                    textView5.setText(min);







                    String stringBuilder = "Country: " +
                            weatherResponse.sys.country +
                            "\n" + "City: " + weatherResponse.name +
                            "Temperature: " +
                            weatherResponse.main.temp +
                            "\n" +
                            "Temperature(Min): " +
                            weatherResponse.main.temp_min +
                            "\n" +
                            "Temperature(Max): " +
                            weatherResponse.main.temp_max +
                            "\n" +
                            "Humidity: " +
                            weatherResponse.main.humidity +
                            "\n" +
                            "Pressure: " +
                            weatherResponse.main.pressure;

//                    weatherData.setText(stringBuilder);
                }
            }

            @Override
            public void onFailure(@NonNull Call<WeatherResponse> call, @NonNull Throwable t) {
                weatherData.setText(t.getMessage());
            }
        });
    }
}
