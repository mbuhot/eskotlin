/*
 * Copyright (c) 2016. Michael Buhot m.buhot@gmail.com
 */

package mbuhot.eskotlin.query.term

import mbuhot.eskotlin.query.QueryData
import mbuhot.eskotlin.query.initQuery
import org.elasticsearch.index.query.TermQueryBuilder

class TermBlock {
    class TermData(
            var name: String? = null,
            var value: String? = null) : QueryData()

    infix fun String.to(value: String): TermData {
        return TermData(name = this, value = value)
    }

    infix fun String.to(init: TermData.() -> Unit): TermData {
        return TermData(name = this).apply(init)
    }
}

fun term(shouldApply: Boolean = true, init: TermBlock.() -> TermBlock.TermData): TermQueryBuilder? {
    if (shouldApply) {
        val params = TermBlock().init()
        return TermQueryBuilder(params.name, params.value).apply {
            initQuery(params)
        }
    } else return null
}