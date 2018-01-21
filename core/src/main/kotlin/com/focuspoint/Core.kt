package com.focuspoint

import io.reactivex.Single

class Core {
    fun print() : String{


        val single : Single<Int> = Single.just(1)


        single.subscribe(
                {num -> { println("String test $num")}},
                {e -> println(e)}
        )

        return "hello from Core!!1" + Single.just("1").blockingGet()
    }
}