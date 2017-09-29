package com.example.nagendran_android.matrimony_task;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by ItropeAndroid on 9/28/2017.
 */

public interface APiService {

    @GET("/user/list/recently-joined/")
    Call<List<Album>> getalbumdetails();
}
