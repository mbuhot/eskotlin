/*
 * Copyright (c) 2016. Michael Buhot m.buhot@gmail.com
 */

package mbuhot.eskotlin.query.term

import mbuhot.eskotlin.query.QueryData
import mbuhot.eskotlin.query.initQuery
import org.elasticsearch.index.query.RegexpFlag
import org.elasticsearch.index.query.RegexpQueryBuilder

class RegexpBlock {
    class RegexpData(
            val name: String,
            var value: String? = null,
            var flags: List<RegexpFlag>? = null,
            var max_determinized_states: Int? = null) : QueryData()

    infix fun String.to(value: String) = RegexpData(name = this, value = value)

    infix fun String.to(init: RegexpData.() -> Unit): RegexpData =
            RegexpData(name = this).apply(init)
}

fun regexp(shouldApply: Boolean = true, init: RegexpBlock.() -> RegexpBlock.RegexpData): RegexpQueryBuilder? {
    if (shouldApply) {
        val params = RegexpBlock().init()
        return RegexpQueryBuilder(params.name, params.value).apply {
            initQuery(params)
            params.flags?.let { flags(*it.toTypedArray()) }
            params.max_determinized_states?.let { maxDeterminizedStates(it) }
        }
    } else return null
}

