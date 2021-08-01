package com.fridayapp.attendancetracker.Services;

import com.fridayapp.attendancetracker.Model.EmpData;
import com.fridayapp.attendancetracker.Model.User;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ServiceApi {

    @GET("register.php")
    Call<User> doRegistration(@Query("name") String name,
                              @Query("email") String email,
                              @Query("image") String image,
                              @Query("password") String password);

    @GET("login.php")
    Call<User> doLogin(@Query("email") String email,
                       @Query("password") String password);

    @FormUrlEncoded
    @POST("emp_data.php")
    Call<EmpData> insertEmpData(
            @Field("emp_data_id") int emp_data_id,
            @Field("emp__no") int emp__no,
            @Field("created_date") String created_date,
            @Field("update_date") String update_date,
            @Field("longitude") String longitude,
            @Field("latitude") String latitude);


}
