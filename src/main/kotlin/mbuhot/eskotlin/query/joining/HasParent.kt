/*
 * Copyright (c) 2016. Michael Buhot m.buhot@gmail.com
 */

package mbuhot.eskotlin.query.joining

import org.elasticsearch.index.query.HasParentQueryBuilder
import org.elasticsearch.index.query.InnerHitBuilder
import org.elasticsearch.index.query.QueryBuilder

data class HasParentData(
        var query: QueryBuilder? = null,
        var parent_type: String? = null,
        var score: Boolean = false,
        var boost: Float? = null,
        var inner_hits: InnerHitBuilder? = null) {

    fun query(f: () -> QueryBuilder?) {
        f()?.let { query = it }
    }
}

fun has_parent(init: HasParentData.() -> Unit): HasParentQueryBuilder {
    val params = HasParentData().apply(init)
    return HasParentQueryBuilder(params.parent_type, params.query, params.score).apply {
        params.boost?.let { boost(it) }
        params.inner_hits?.let { innerHit(it) }
    }
}