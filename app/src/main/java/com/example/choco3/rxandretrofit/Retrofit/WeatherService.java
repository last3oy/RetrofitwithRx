package com.example.choco3.rxandretrofit.Retrofit;

import com.example.choco3.rxandretrofit.Model.WeatherData;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Choco3 on 4/8/2559.
 */
public interface WeatherService {

    @GET("weather")
    Observable<WeatherData> getWeatherData(@Query("q") String city, @Query("APPID") String id);
}
