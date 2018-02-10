package com.focuspoint.capomap

import com.focuspoint.Core
import com.google.gson.JsonParser

fun main(args: Array<String>) {

    val login = "admin"
    val response = Core().preAuth(login).blockingGet()

    println(response)
    val obj = JsonParser().parse(response.body()?.string()).asJsonObject
    val key = obj.getAsJsonObject("Result").getAsJsonPrimitive("key")

    val authResponse = Core().auth(login, "HpX988y61", key.asString).blockingGet()

    val eventResponse = Core().getList().blockingGet()




    println(authResponse.body()?.string())
    println(eventResponse.body()?.string())

}