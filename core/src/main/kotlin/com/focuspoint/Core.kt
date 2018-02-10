package com.focuspoint

import com.focuspoint.network.TestInterface
import com.google.gson.Gson
import com.google.gson.JsonObject
import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.nio.charset.StandardCharsets
import java.security.MessageDigest

class Core {
    fun print() : String{




        val single : Single<Int> = Single.just(1)


        single.subscribe(
                {num -> { println("String test $num")}},
                {e -> println(e)}
        )

        return "hello from Core!!1" + Single.just("1").blockingGet()
    }

    fun auth(login : String, password: String, key : String) : Single<Response<ResponseBody>>{
        val retrofit : Retrofit = Retrofit.Builder()
                .baseUrl("http://randomus.gq:8000/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

        val service : TestInterface = retrofit.create(TestInterface::class.java)

        println("login = admin")
        val sum =  password +  key

        println("password + key = " + sum)
        println("sagrade = " + hashString("SHA-256", sum))

        val loginJSON = JsonObject()
        loginJSON.addProperty("login", login)
        loginJSON.addProperty("sagrade", hashString("SHA-256", sum))

        return service.call("auth", "auth", loginJSON.toString())
    }


    fun preAuth(login : String) : Single<Response<ResponseBody>>{
        val retrofit : Retrofit = Retrofit.Builder()
                .baseUrl("http://randomus.gq:8000/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

        val service : TestInterface = retrofit.create(TestInterface::class.java)

        val loginJSON = JsonObject()
        loginJSON.addProperty("login", login)


        return service.call("auth", "preAuth", loginJSON.toString())
    }


    fun getList() : Single<Response<ResponseBody>>{
        val retrofit : Retrofit = Retrofit.Builder()
                .baseUrl("http://randomus.gq/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()


        val service : TestInterface = retrofit.create(TestInterface::class.java)
        return service.call("event", "getList")
    }


    private fun hashString(type: String, input: String): String {
        val HEX_CHARS = "0123456789ABCDEF"
        val bytes = MessageDigest
                .getInstance(type)
                .digest(input.toByteArray())
        val result = StringBuilder(bytes.size * 2)

        bytes.forEach {
            val i = it.toInt()
            result.append(HEX_CHARS[i shr 4 and 0x0f])
            result.append(HEX_CHARS[i and 0x0f])
        }

        return result.toString()
    }

}