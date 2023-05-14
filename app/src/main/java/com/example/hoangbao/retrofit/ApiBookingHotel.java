package com.example.hoangbao.retrofit;


import com.example.hoangbao.Model.BookRoomModel;
import com.example.hoangbao.Model.RoomModel;
import com.example.hoangbao.Model.ShowRoomModel;
import com.example.hoangbao.Model.UserModel;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiBookingHotel {
    @GET("getData.php")
    Observable<RoomModel> getRoom();

    @POST("dangnhap.php")
    @FormUrlEncoded
    Observable<UserModel> dangNhap(
            @Field("email") String email,
            @Field("password") String pass
    );

    @POST("dangki.php")
    @FormUrlEncoded
    Observable<UserModel> dangKi(
            @Field("name") String name,
            @Field("email") String email,
            @Field("password") String pass
    );

    @POST("show.php")
    @FormUrlEncoded
    Observable<ShowRoomModel> showRoom(
            @Field("maKH") int maKH,
            @Field("status") int status
    );

    @POST("delete.php")
    @FormUrlEncoded
    Observable<ShowRoomModel> deleteBooking(
            @Field("id") int id
    );

    @POST("order.php")
    @FormUrlEncoded
    Observable<BookRoomModel> bookRoom(
            @Field("maKH") int maKH,
            @Field("startDate") String startDate,
            @Field("night") String night,
            @Field("sophong") String sophong
    );
}
