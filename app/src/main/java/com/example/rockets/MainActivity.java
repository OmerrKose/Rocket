package com.example.rockets;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;
import okhttp3.Callback;


public class MainActivity extends AppCompatActivity {

    RestInterface restInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        restInterface = ApiClient.getClient().create(RestInterface.class);

        retrofit2.Call<List<Repository>> call = restInterface.getRepo();

        call.enqueue(new Callback<List<Repository>>() {
            @Override
            public void onResponse(Call<List<Repository>> call, Response<List<Repository>> response) {
                List<Repository> repoList=new ArrayList<>();
                repoList=response.body();
                for (int i=0;i<repoList.size();i++){
                    System.out.println(""+repoList.get(i).rocket.rocketId+"\n");
                }
            }
            @Override
            public void onFailure(Call<List<Repository>> call, Throwable t) {
            }
        });
    }
}
