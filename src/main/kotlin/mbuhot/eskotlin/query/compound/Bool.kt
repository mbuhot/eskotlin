/*
 * Copyright (c) 2016. Michael Buhot m.buhot@gmail.com
 */

package mbuhot.eskotlin.query.compound

import mbuhot.eskotlin.query.ListQueriesBlock
import org.elasticsearch.index.query.BoolQueryBuilder
import org.elasticsearch.index.query.QueryBuilder


data class BoolData(
    var must: List<QueryBuilder>? = null,
    var filter: List<QueryBuilder>? = null,
    var must_not: List<QueryBuilder>? = null,
    var should: List<QueryBuilder>? = null,
    var minimum_should_match: Int? = null,
    var boost: Float? = null) {

    fun must(init: ListQueriesBlock.() -> Unit) {
        must = ListQueriesBlock().apply(init).queries
    }

    fun must_not(init: ListQueriesBlock.() -> Unit) {
        must_not = ListQueriesBlock().apply(init).queries
    }

    fun filter(init: ListQueriesBlock.() -> Unit) {
        filter = ListQueriesBlock().apply(init).queries
    }

    fun should(init: ListQueriesBlock.() -> Unit) {
        should = ListQueriesBlock().apply(init).queries
    }
}

fun bool(init: BoolData.() -> Unit): BoolQueryBuilder {
    val params = BoolData().apply(init)
    return BoolQueryBuilder().apply {
        params.must?.forEach { must(it) }
        params.filter?.forEach { filter(it) }
        params.must_not?.forEach { mustNot(it) }
        params.should?.forEach { should(it) }
        params.minimum_should_match?.let { minimumShouldMatch(it) }
        params.boost?.let { boost(it) }
    }
}