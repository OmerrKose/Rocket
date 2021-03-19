package com.example.rockets;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import kotlin.jvm.internal.markers.KMutableList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    RestInterface restInterface;
    private Object Rocket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        restInterface = ApiClient.getClient().create(RestInterface.class);

        Call<List<Repository>> call = restInterface.getRepo();

        call.enqueue(new Callback<List<Repository>>() {
            @Override
            public void onResponse(Call<List<Repository>> call, Response<List<Repository>> response) {
                List<Repository> repoList = new ArrayList<>();
                repoList = response.body();
                for (int i = 0; i < repoList.size(); i++) {
                    System.out.println("" + repoList.get(i).rocket.rocketId + "\n");
                }
            }

            @Override
            public void onFailure(Call<List<Repository>> call, Throwable t) {
                //no-op
            }
        });

        public rv; //TODO how to create variables?
        rv = findViewById(R.id.recyclerView);
        Adapter rocketAdapter;
        rocketAdapter = Adapter(KMutableList(Rocket));
        Context context = this;
        rv.apply(); //TODO .apply method
            layoutManager = new LinearLayoutManager(context, RecyclerView.VERTICAL, false);
            Adapter rocketAdapter1 = rocketAdapter;
        }
    }
}
