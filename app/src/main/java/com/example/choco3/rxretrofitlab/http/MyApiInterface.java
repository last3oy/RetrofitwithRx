package com.example.choco3.rxretrofitlab.http;


import com.example.choco3.rxretrofitlab.model.GitHubFollowerUser;
import com.example.choco3.rxretrofitlab.model.GitHubUser;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Choco3 on 30/9/2559.
 */

public interface MyApiInterface {

    @GET("users/{user}/followers")
    Observable<List<GitHubFollowerUser>> loadFollowerUser(@Path("user") String user);

    @GET("users/{user}")
    Observable<GitHubUser> loadUser(@Path("user") String user);

}
