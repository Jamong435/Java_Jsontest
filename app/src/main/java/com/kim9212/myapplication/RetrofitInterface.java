package com.kim9212.myapplication;


import java.util.Map;


import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.Call;


public interface RetrofitInterface {

    @GET("/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json")
    Call<Map<String,Object>> getBoxOffice(@Query("key") String key,
                                          @Query("targetDt") String targetDt);}

