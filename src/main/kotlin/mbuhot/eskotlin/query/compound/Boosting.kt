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

    fun positive(f: () -> QueryBuilder?) {
        f()?.let { positive = it }
    }

    fun negative(f: () -> QueryBuilder?) {
        f()?.let { negative = it }
    }
}

fun boosting(init: BoostingData.() -> Unit): BoostingQueryBuilder {
    val params = BoostingData().apply(init)
    return BoostingQueryBuilder(params.positive, params.negative).apply {
        params.negative_boost?.let { negativeBoost(it) }
    }
}
