/*
 * Copyright (c) 2017. Michael Buhot m.buhot@gmail.com
 */

package mbuhot.eskotlin.query.util

/**
 * Created by mbuhot on 11/2/17.
 */

/**
 * Simlar to kotlin stdlib run, runs a block conditionally on a boolean.
 */
fun <T, R> T.runIf(condition: Boolean, f: T.() -> R): R? {
    return if (condition) {
        f()
    } else {
        null
    }
}