/*
 * Copyright (c) 2016. Michael Buhot m.buhot@gmail.com
 */

package mbuhot.eskotlin.query.compound

import org.elasticsearch.index.query.BoostingQueryBuilder
import org.elasticsearch.index.query.QueryBuilder

data class BoostingData(
    var positive: QueryBuilder? = null,
    var negative: QueryBuilder? = null,
    var negative_boost: Float? = null) {

    fun positive(shouldApply: Boolean = true, f: () -> QueryBuilder?) {
        if (shouldApply) f()?.let { positive = it }
    }

    fun negative(shouldApply: Boolean = true, f: () -> QueryBuilder?) {
        if (shouldApply) f()?.let { negative = it }
    }
}

fun boosting(shouldApply: Boolean = true, init: BoostingData.() -> Unit): BoostingQueryBuilder? {
    if (shouldApply) {
        val params = BoostingData().apply(init)
        return BoostingQueryBuilder(params.positive, params.negative).apply {
            params.negative_boost?.let { negativeBoost(it) }
        }
    } else return null
}
