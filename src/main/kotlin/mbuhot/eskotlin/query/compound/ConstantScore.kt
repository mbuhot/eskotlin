/*
 * Copyright (c) 2016. Michael Buhot m.buhot@gmail.com
 */

package mbuhot.eskotlin.query.compound

import org.elasticsearch.index.query.ConstantScoreQueryBuilder
import org.elasticsearch.index.query.QueryBuilder


data class ConstantScoreData(
        var filter: QueryBuilder? = null,
        var boost: Float? = null) {
    fun filter(shouldApply: Boolean = true, f: () -> QueryBuilder?) {
        if (shouldApply) f()?.let { filter = it }
    }
}

fun constant_score(shouldApply: Boolean = true, init: ConstantScoreData.() -> Unit): ConstantScoreQueryBuilder? {
    if (shouldApply) {
        val params = ConstantScoreData().apply(init)
        return ConstantScoreQueryBuilder(params.filter).apply {
            params.boost?.let { boost(it) }
        }
    } else return null
}
