package com.vishalpvijayan.khoslalabtaskvishal;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

// WEATHER_CLIENT_INTERFACE

public interface WeatherService {
    @GET("data/2.5/weather?")
    Call<WeatherResponse> getCurrentWeatherData(@Query("lat") String lat, @Query("lon") String lon, @Query("APPID") String app_id);

}

