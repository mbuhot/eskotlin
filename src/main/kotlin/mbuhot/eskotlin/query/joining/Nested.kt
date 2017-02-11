/*
 * Copyright (c) 2016. Michael Buhot m.buhot@gmail.com
 */

package mbuhot.eskotlin.query.joining

import org.apache.lucene.search.join.ScoreMode
import org.elasticsearch.index.query.InnerHitBuilder
import org.elasticsearch.index.query.NestedQueryBuilder
import org.elasticsearch.index.query.QueryBuilder


data class NestedData(
        var query: QueryBuilder? = null,
        var path: String? = null,
        var score_mode: ScoreMode = ScoreMode.Avg,
        var boost: Float? = null,
        var inner_hits: InnerHitBuilder? = null) {

    fun query(f: () -> QueryBuilder?) {
        f()?.let { query = it }
    }
}

fun nested(init: NestedData.() -> Unit): NestedQueryBuilder {
    val params = NestedData().apply(init)
    return NestedQueryBuilder(params.path, params.query, params.score_mode).apply {
        params.boost?.let { boost(it) }
        params.inner_hits?.let { innerHit(it) }
    }
}