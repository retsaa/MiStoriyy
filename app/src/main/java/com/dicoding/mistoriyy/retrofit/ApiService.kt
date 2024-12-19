package com.dicoding.mistoriyy.retrofit

import com.dicoding.mistoriyy.Detail.DetailResponse
import com.dicoding.mistoriyy.login.LoginResponse
import com.dicoding.mistoriyy.signup.SignupResponse
import com.dicoding.mistoriyy.storiyy.addStoriy.AddStoriyResponse
import com.dicoding.mistoriyy.storiyy.StoriyResponse
import okhttp3.*
import retrofit2.http.*

interface ApiService {
    @FormUrlEncoded
    @POST("register")
    suspend fun signupUser(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): SignupResponse

    @FormUrlEncoded
    @POST("login")
    suspend fun loginUser(
        @Field("email") email: String,
        @Field("password") password: String
    ): LoginResponse

    @GET("stories")
    suspend fun getStories(): StoriyResponse

    @GET("stories/{id}")
    suspend fun getDetailStories(
        @Path("id") id: String
    ): DetailResponse

    @Multipart
    @POST("stories")
    suspend fun addStutori(
        @Part file: MultipartBody.Part,
        @Part("description") description: RequestBody,
    ): AddStoriyResponse

    @GET("stories")
    suspend fun getStoriyLocation(
        @Query("location") location: Int = 1
    ): StoriyResponse
}