package com.example.rockets;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    RestInterface restInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView rv = this.findViewById(R.id.recyclerView);


        restInterface = ApiClient.getClient().create(RestInterface.class);
        Call<List<Repository>> call = restInterface.getRepo();

        List<Rocket> rocketList = new ArrayList<>();

        call.enqueue(new Callback<List<Repository>>() {
            @Override
            public void onResponse(Call<List<Repository>> call, Response<List<Repository>> response) {
                List<Repository> repoList = new ArrayList<>();
                repoList = response.body();
                for (int i = 0; i < repoList.size(); i++) {
                    System.out.println("" + repoList.get(i).rocket.rocketId + "\n");
                    rocketList.add(new Rocket(
                            repoList.get(i).rocket.rocketId,
                            repoList.get(i).rocket.rocketName,
                            repoList.get(i).rocket.rocketType)
                    );
                }
                Adapter rocketAdapter = new Adapter(rocketList);
                LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
                rv.setAdapter(rocketAdapter);
                rv.setLayoutManager(layoutManager);
            }

            @Override
            public void onFailure(Call<List<Repository>> call, Throwable t) {
                //no-op
            }
        });
    }
}
