package com.focuspoint

import com.focuspoint.network.TestInterface
import com.google.gson.Gson
import com.google.gson.JsonObject
import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

class Core {
    fun print() : String{




        val single : Single<Int> = Single.just(1)


        single.subscribe(
                {num -> { println("String test $num")}},
                {e -> println(e)}
        )

        return "hello from Core!!1" + Single.just("1").blockingGet()
    }


    fun auth() : Single<Response<ResponseBody>>{
        val retrofit : Retrofit = Retrofit.Builder()
                .baseUrl("http://randomus.gq/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()


        val service : TestInterface = retrofit.create(TestInterface::class.java)

        val login : JsonObject = JsonObject()
        login.addProperty("login","vova")


        return service.call("auth", "preAuth", login.toString())
    }


    fun getList() : Single<Response<ResponseBody>>{
        val retrofit : Retrofit = Retrofit.Builder()
                .baseUrl("http://randomus.gq/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()


        val service : TestInterface = retrofit.create(TestInterface::class.java)
        return service.call("event", "call")
    }
}