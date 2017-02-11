/*
 * Copyright (c) 2016. Michael Buhot m.buhot@gmail.com
 */

package mbuhot.eskotlin.query.compound

import org.elasticsearch.index.query.BoolQueryBuilder
import org.elasticsearch.index.query.QueryBuilder


data class BoolData(
        var must: List<QueryBuilder?>? = null,
        var filter: List<QueryBuilder?>? = null,
        var must_not: List<QueryBuilder?>? = null,
        var should: List<QueryBuilder?>? = null,
        var minimum_should_match: Int? = null,
        var boost: Float? = null) {

    fun must(f: () -> QueryBuilder?) {
        f()?.let { must = listOf(it) }
    }

    fun must_not(f: () -> QueryBuilder?) {
        f()?.let { must_not = listOf(it) }
    }

    fun filter(f: () -> QueryBuilder?) {
        f()?.let { filter = listOf(it) }
    }

    fun should(f: () -> QueryBuilder?) {
        f()?.let { should = listOf(it) }
    }
}

fun bool(init: BoolData.() -> Unit): BoolQueryBuilder {
    val params = BoolData().apply(init)
    return BoolQueryBuilder().apply {
        params.must?.filterNotNull()?.forEach { must(it) }
        params.filter?.filterNotNull()?.forEach { filter(it) }
        params.must_not?.filterNotNull()?.forEach { mustNot(it) }
        params.should?.filterNotNull()?.forEach { should(it) }
        params.minimum_should_match?.let { minimumNumberShouldMatch(it) }
        params.boost?.let { boost(it) }
    }
}