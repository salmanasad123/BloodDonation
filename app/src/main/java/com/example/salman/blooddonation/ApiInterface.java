package com.example.salman.blooddonation;

import android.text.Editable;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by Salman on 12/13/2017.
 */

public interface ApiInterface {


    @GET("api/donors")
    Call<List<Donor>> getDonors();

    @GET("api/donors/{id}")
    Call<Donor> getDonor(@Path("id") int id);

    @POST("api/donors")
    @FormUrlEncoded
    Call<Donor> saveDonor(@Field("first_name") String first_name,
                          @Field("last_name") String last_name,
                          @Field("phone_number") Editable phone_number,
                          @Field("age") Editable age,
                          @Field("email") String email,
                          @Field("blood_group_id") Editable blood_group_id);

    // @POST("api/donors")
    //Call<Donor> saveDonor(@Body Donor donor);

   /* @PUT("api/donors/{id}")
    @FormUrlEncoded
    Call<Donor> updateDonor(@Path("id") int id,
                            @Field("first_name") String first_name);
                            //@Field("last_name") String last_name,
                            //@Field("phone_number") Editable phone_number,
                            //@Field("age") Editable age,
                            //@Field("email") Editable email);
*/

    @PUT("api/donors/{id}")
    Call<Donor> updateDonor(@Path("id") int id, @Body Donor donor);

    @DELETE("api/donors/{id}")
    Call<Donor> deleteDonor(@Path("id") Editable id);

}
