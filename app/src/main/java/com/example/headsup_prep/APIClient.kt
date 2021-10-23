package com.example.headsup_prep
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Headers

class APIClient {
    private var retrofit: Retrofit? = null

    fun getClient(): Retrofit? {

        // we will skip the interceptor because we won't need it here
        retrofit = Retrofit.Builder()
            .baseUrl("https://dojo-recipes.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit
    }
}