/*
 * Copyright (c) 2016. Michael Buhot m.buhot@gmail.com
 */

package mbuhot.eskotlin.query.compound

import org.elasticsearch.index.query.ConstantScoreQueryBuilder
import org.elasticsearch.index.query.QueryBuilder


data class ConstantScoreData(
        var filter: QueryBuilder? = null,
        var boost: Float? = null) {
    fun filter(f: () -> QueryBuilder?) {
        f()?.let { filter = it }
    }
}

fun constant_score(init: ConstantScoreData.() -> Unit): ConstantScoreQueryBuilder {
    val params = ConstantScoreData().apply(init)
    return ConstantScoreQueryBuilder(params.filter).apply {
        params.boost?.let { boost(it) }
    }
}
