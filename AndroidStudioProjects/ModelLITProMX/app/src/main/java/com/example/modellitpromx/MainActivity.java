package com.example.modellitpromx;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.modellitpromx.api.OpenApiService;
import com.example.modellitpromx.models.AccountProperties;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements FeedRecViewAdapter.OnFeedListener {

    private static final String TAG = "MainActivity";
    private RecyclerView feedRecView;
    private Button printLogBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        printLogBtn = findViewById(R.id.printLogButton);


        feedRecView = findViewById(R.id.feedRecView);
        FeedRecViewAdapter adapter = new FeedRecViewAdapter(this, this);

        Utils.initFeed();
        ArrayList<Feed> feedData = Utils.getFeeds();
        if(null != feedData) {
            adapter.setFeed(feedData);
        }

        feedRecView.setAdapter(adapter);
        feedRecView.setLayoutManager(new LinearLayoutManager(this));

        //Adding retrofit instance and Api service

        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();

        getRetrofitInstance(gson);



        printLogBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                safeApiCall(openApiService, retrofitBuilder);
            }
        });


    }

    private static Retrofit getRetrofitInstance(Gson gson) {


        Retrofit retrofit = null;

        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl("https://api3.nznlabs.com/")
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }

    private void safeApiCall(OpenApiService openApiService, R Retrofit.Retrofit.Builder retrofitBuilder) {
        Log.d(TAG, "safeApiCall: start");
        Call<AccountProperties> call = (Call<AccountProperties>) openApiService.getAccountProperties("jacobpzinn@gmail.com", "077454edf4bf8bd07430de53cc2dea185dde1e09");
        Log.d(TAG, "safeApiCall: 1");
        call.enqueue(new Callback<AccountProperties>() {
            @Override
            public void onResponse(Call<AccountProperties> call, Response<AccountProperties> response) {
                Log.d(TAG, "onResponse: 2");
                int statusCode = response.code();
                AccountProperties accountProperties = response.body();
                System.out.println(accountProperties.getEmail()
                        + accountProperties.getFirstName() + accountProperties.getLastName());

            }

            @Override
            public void onFailure(Call<AccountProperties> call, Throwable t) {
                Log.d(TAG, "onFailure: 3");
                Log.d(TAG, "onFailure: could not receive account properties");
                // Log error here since request failed
            }
        });
    }

    @Override
    public void onFeedClick(int position, String filterSetting) {
        Log.d(TAG, "onFeedClick: 1");
        /* TODO when the database is connected, the onFeedClick method will need to receive an id
            for the particular session rather than the position within the rec view adapter.
            The id will be passed with the
            intent in order to set the filter correctly. There will still be an if else
            necessary to determine whether the user clicked on the image or sessionSummary.
         */

                if (filterSetting.equals("singleDay")) {
                    Log.d(TAG, "onFeedClick: SingleDayFilter");
                }
                else {
                    Log.d(TAG, "onFeedClick: All days filter");
                }

        Intent intent = new Intent(this, SessionsActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

    }
}