/*
 * Copyright (c) 2016. Michael Buhot m.buhot@gmail.com
 */

package mbuhot.eskotlin.query.term

import mbuhot.eskotlin.query.QueryData
import mbuhot.eskotlin.query.initQuery
import org.elasticsearch.index.query.PrefixQueryBuilder

class PrefixBlock {
    class PrefixData(
            val name: String,
            var prefix: String? = null) : QueryData()

    infix fun String.to(prefix: String) = PrefixData(name = this, prefix = prefix)

    infix fun String.to(init: PrefixData.() -> Unit): PrefixData =
            PrefixData(name = this).apply(init)
}

fun prefix(shouldApply: Boolean = true, init: PrefixBlock.() -> PrefixBlock.PrefixData): PrefixQueryBuilder? {
    if (shouldApply) {
        val params = PrefixBlock().init()
        return PrefixQueryBuilder(params.name, params.prefix).apply {
            initQuery(params)
        }
    } else return null
}