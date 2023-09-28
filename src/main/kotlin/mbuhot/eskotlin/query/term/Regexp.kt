/*
 * Copyright (c) 2016. Michael Buhot m.buhot@gmail.com
 */

package mbuhot.eskotlin.query.term

import mbuhot.eskotlin.query.QueryData
import mbuhot.eskotlin.query.initQuery
import org.opensearch.index.query.RegexpFlag
import org.opensearch.index.query.RegexpQueryBuilder

class RegexpBlock {
    class RegexpData(
            val name: String,
            var value: String? = null,
            var flags: List<RegexpFlag>? = null,
            var max_determinized_states: Int? = null) : QueryData()

    infix fun String.to(value: String) = RegexpData(name = this, value = value)

    @Deprecated(message = "Use invoke operator instead.", replaceWith = ReplaceWith("invoke(init)"))
    infix fun String.to(init: RegexpData.() -> Unit) = this.invoke(init)

    operator fun String.invoke(init: RegexpData.() -> Unit) = RegexpData(name = this).apply(init)
}

fun regexp(init: RegexpBlock.() -> RegexpBlock.RegexpData): RegexpQueryBuilder {
    val params = RegexpBlock().init()
    return RegexpQueryBuilder(params.name, params.value).apply {
        initQuery(params)
        params.flags?.let { flags(*it.toTypedArray()) }
        params.max_determinized_states?.let { maxDeterminizedStates(it) }
    }
}

