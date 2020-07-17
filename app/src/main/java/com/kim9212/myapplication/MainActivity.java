package com.kim9212.myapplication;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    String baseUrl = "http://www.kobis.or.kr";
    String API_KEY = "b2fcdd211f6519d04f4f4586e36a746c";
    Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=findViewById(R.id.rv_recyclerview);

        //Retrofit 객체생성
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitInterface retrofitInterface= retrofit.create(RetrofitInterface.class);

        retrofitInterface.getBoxOffice(API_KEY,"20200319").enqueue(new Callback<Map<String, Object>>() {
            @Override
            public void onResponse(Call<Map<String, Object>> call, Response<Map<String, Object>> response) {

                Map<String,Object> boxOfficeResult= (Map<String, Object>) response.body().get("boxOfficeResult");
                ArrayList<Map<String, Object>> jsonList = (ArrayList) boxOfficeResult.get("dailyBoxOfficeList");
                mAdapter=new MyAdapter(jsonList);

            }

            @Override
            public void onFailure(Call<Map<String, Object>> call, Throwable t) {

            }
        });

    }// onCreate()..

    public void click_btn(View view) {
        recyclerView.setAdapter(mAdapter);
    }

}// MainActivity class..


