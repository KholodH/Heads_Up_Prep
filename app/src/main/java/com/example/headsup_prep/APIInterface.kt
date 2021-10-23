package com.example.headsup_prep

import android.text.Editable
import retrofit2.Call
import retrofit2.http.*

interface APIInterface {
    //https://dojo-recipes.herokuapp.com/celebrities/
    // we don't need headers for this demo
    @Headers("Content-Type: application/json")
    @GET("/celebrities/")
    fun getmem(): Call<ArrayList<celebrities.person>>

    @Headers("Content-Type: application/json")
    @POST("/celebrities/")
    fun addmember(@Body userData: celebrities.person): Call<celebrities.person>

    @POST("/celebrities/")
    fun addCelebrity(@Body celebrityData: celebrities.person): Call<celebrities.person>

    @GET("/celebrities/{id}")
    fun getCelebrity(@Path("id") id: Int): Call<celebrities.person>

    // PUT replaces the full object (use PATCH to change individual fields)
    @PUT("/celebrities/{id}")
    fun updateCelebrity(@Path("id") id: Int, @Body celebrityData: celebrities.person): Call<celebrities.person>

    @DELETE("/celebrities/{id}")
    fun deleteCelebrity(@Path("id") id: Int): Call<Void>
}