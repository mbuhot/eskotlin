/*
 * Copyright (c) 2016. Michael Buhot m.buhot@gmail.com
 */

package mbuhot.eskotlin.query.term

import mbuhot.eskotlin.query.QueryData
import mbuhot.eskotlin.query.initQuery
import org.elasticsearch.index.query.WildcardQueryBuilder

class WildcardBlock {
    class WildcardData(
            val name: String,
            var wildcard: String? = null) : QueryData()

    infix fun String.to(wildcard: String) = WildcardData(name = this, wildcard = wildcard)

    infix fun String.to(init: WildcardData.() -> Unit): WildcardData =
            WildcardData(name = this).apply(init)
}

fun wildcard(shouldApply: Boolean = true, init: WildcardBlock.() -> WildcardBlock.WildcardData): WildcardQueryBuilder? {
    if (shouldApply) {
        val params = WildcardBlock().init()
        return WildcardQueryBuilder(params.name, params.wildcard).apply {
            initQuery(params)
        }
    } else return null
}

