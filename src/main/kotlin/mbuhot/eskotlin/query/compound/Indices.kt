/*
 * Copyright (c) 2016. Michael Buhot m.buhot@gmail.com
 */

package mbuhot.eskotlin.query.compound

import org.elasticsearch.index.query.IndicesQueryBuilder
import org.elasticsearch.index.query.QueryBuilder

data class IndicesData(
        var indices: List<String> = emptyList(),
        var query: QueryBuilder? = null,
        var no_match_query: String? = null,
        var no_match_query_builder: QueryBuilder? = null) {

    fun query(shouldApply: Boolean = true, f: () -> QueryBuilder?) {
        if (shouldApply) f()?.let { query = it }
    }

    fun no_match_query(shouldApply: Boolean = true, f: () -> QueryBuilder?) {
        if (shouldApply) f()?.let { no_match_query_builder = it }
    }
}


fun indices(shouldApply: Boolean = true, init: IndicesData.() -> Unit): IndicesQueryBuilder? {
    if (shouldApply) {
        val params = IndicesData().apply(init)
        return IndicesQueryBuilder(params.query, *params.indices.toTypedArray()).apply {
            params.no_match_query?.let { noMatchQuery(it) }
            params.no_match_query_builder?.let { noMatchQuery(it) }
        }
    } else return null
}

