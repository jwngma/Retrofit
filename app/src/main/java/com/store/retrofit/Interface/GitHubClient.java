package com.store.retrofit.Interface;

import com.store.retrofit.Model.GitHubRepo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GitHubClient {

    @GET("/users")
    Call<List<GitHubRepo>> reposForUser();
}
