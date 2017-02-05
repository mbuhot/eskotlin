/*
 * Copyright (c) 2016. Michael Buhot m.buhot@gmail.com
 */

package mbuhot.eskotlin.query.term

import mbuhot.eskotlin.query.QueryData
import mbuhot.eskotlin.query.initQuery
import org.elasticsearch.index.query.TermsQueryBuilder

class TermsBlock {
    class TermsData(
            var name: String,
            var values: List<Any>) : QueryData()

    infix fun String.to(values: List<Any>): TermsData {
        return TermsData(name = this, values = values)
    }
}

fun terms(shouldApply: Boolean = true, init: TermsBlock.() -> TermsBlock.TermsData): TermsQueryBuilder? {
    if (shouldApply) {
        val params = TermsBlock().init()
        return TermsQueryBuilder(params.name, *params.values.toTypedArray()).apply {
            initQuery(params)
        }
    } else return null
}