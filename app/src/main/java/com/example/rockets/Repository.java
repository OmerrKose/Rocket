package com.example.rockets;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Repository {
    @SerializedName("rocket")
    @Expose
    public Rocket rocket;
}
