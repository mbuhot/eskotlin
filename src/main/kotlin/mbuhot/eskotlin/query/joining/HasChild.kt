/*
 * Copyright (c) 2016. Michael Buhot m.buhot@gmail.com
 */

package mbuhot.eskotlin.query.joining

import org.apache.lucene.search.join.ScoreMode
import org.elasticsearch.index.query.HasChildQueryBuilder
import org.elasticsearch.index.query.InnerHitBuilder
import org.elasticsearch.index.query.QueryBuilder

data class HasChildData(
        var query: QueryBuilder? = null,
        var type: String? = null,
        var boost: Float? = null,
        var score_mode: ScoreMode = ScoreMode.None,
        var min_children: Int = HasChildQueryBuilder.DEFAULT_MIN_CHILDREN,
        var max_children: Int = HasChildQueryBuilder.DEFAULT_MAX_CHILDREN,
        var inner_hits: InnerHitBuilder? = null) {

    fun query(f: () -> QueryBuilder?) {
        f()?.let { query = it }
    }
}

fun has_child(init: HasChildData.() -> Unit): HasChildQueryBuilder {
    val params = HasChildData().apply(init)
    return HasChildQueryBuilder(params.type, params.query, params.score_mode).apply {
        params.boost?.let { boost(it) }
        minMaxChildren(params.min_children, params.max_children)
        params.inner_hits?.let { innerHit(it) }
    }
}