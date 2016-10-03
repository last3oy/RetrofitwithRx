package com.example.choco3.rxandretrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.choco3.rxandretrofit.Model.Github;
import com.example.choco3.rxandretrofit.Model.WeatherData;
import com.example.choco3.rxandretrofit.Retrofit.GithubService;
import com.example.choco3.rxandretrofit.Retrofit.WeatherService;

import org.w3c.dom.Text;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func2;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    TextView tvGithubUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvGithubUsername = (TextView) findViewById(R.id.tv_github_username);

        Retrofit retrofit2 = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://api.openweathermap.org/data/2.5/")
                .build();

        WeatherService weatherService = retrofit2.create(WeatherService.class);
        Observable<WeatherData> london = weatherService.getWeatherData("Islamabad", "3b2ebaa566dd31806c014eb51d9a45b9");

        london.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<WeatherData>() {
                    @Override
                    public void onCompleted() {
                        Log.e("Current Weather", "Complete");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("Current Weather", e.getMessage());
                    }

                    @Override
                    public void onNext(WeatherData weatherData) {
                        Log.e("Current Weather", weatherData.getWeather()
                                .get(0)
                                .getDescription());

                        Toast.makeText(MainActivity.this,
                                weatherData.getWeather().get(0).getDescription(),
                                Toast.LENGTH_SHORT)
                                .show();
                    }
                });


        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.github.com/")
                .build();

        GithubService githubService = retrofit.create(GithubService.class);
        Observable<Github> githubUser = githubService.getGithubUser("last3oy");




        githubUser.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Github>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("Github api", e.toString());
                    }

                    @Override
                    public void onNext(Github github) {
                        tvGithubUsername.setText("Name: " + github.getName() + "\nLocation: " + github.getLocation());
                    }
                });



        Observable.zip(london, githubUser, new Func2<WeatherData, Github, Object>() {
            @Override
            public Object call(WeatherData weatherData, Github github) {
                return null;
            }
        }).subscribe(new Observer<Object>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Object o) {

            }
        });
    }
}
