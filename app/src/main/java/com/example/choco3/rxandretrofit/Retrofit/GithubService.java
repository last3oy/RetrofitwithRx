package com.example.choco3.rxandretrofit.Retrofit;

import com.example.choco3.rxandretrofit.Model.Github;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Choco3 on 4/8/2559.
 */
public interface GithubService {
    @GET("users/{username}")
    Observable<Github> getGithubUser(@Path("username") String username);

}
