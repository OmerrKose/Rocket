package com.example.rockets;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import kotlin.jvm.internal.markers.KMutableList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.example.rockets.R.id.recyclerView;
import static com.example.rockets.R.id.rocket;

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

        RecyclerView rv = this.findViewById(recyclerView);

        List<Rocket> rocketList = new ArrayList<>();
        Adapter rocketAdapter = new Adapter(rocketList);

        Context context = this;
        LinearLayoutManager layoutManager = new LinearLayoutManager(context, RecyclerView.VERTICAL, false);

        rv.setAdapter(rocketAdapter);
        rv.setLayoutManager(layoutManager);
    }
}
