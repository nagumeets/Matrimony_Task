package com.example.nagendran_android.matrimony_task;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Lincoln on 18/05/16.
 */
public class Album {
    int id;
    String first_name;
    String city;
    String profile_pic;

    public int getid() {
        return id;
    }

    public void setid(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProfile_pic() {
        return profile_pic;
    }

    public void setProfile_pic(String profile_pic) {
        this.profile_pic = profile_pic;
    }

}
