package com.example.rockets;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Rocket {
        @SerializedName("rocket_id")
        @Expose
        public String rocketId;
        @SerializedName("rocket_name")
        @Expose
        public String rocketName;
        @SerializedName("rocket_type")
        public String rocketType;
}
