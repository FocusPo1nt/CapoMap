package com.focuspoint.capomap

import com.focuspoint.Core

fun main(args: Array<String>) {
    println(Core().auth().blockingGet())
}