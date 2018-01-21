package com.focuspoint.network

import io.reactivex.Observable
import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface TestInterface {

    @GET(".")
    fun call(@Query("object") object_: String,
             @Query("method") event: String): Single<Response<ResponseBody>>


    @GET(".")
    fun call(@Query("object") object_: String,
             @Query("method") event: String,
             @Query("params")params : String)
    : Single<Response<ResponseBody>>

}