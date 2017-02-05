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

    fun must(shouldApply: Boolean = true, f: () -> QueryBuilder?) {
        if (shouldApply) f()?.let { must = listOf(it) }
    }

    fun must_not(shouldApply: Boolean = true, f: () -> QueryBuilder?) {
        if (shouldApply) f()?.let { must_not = listOf(it) }
    }

    fun filter(shouldApply: Boolean = true, f: () -> QueryBuilder?) {
        if (shouldApply) f()?.let { filter = listOf(it) }
    }

    fun should(shouldApply: Boolean = true, f: () -> QueryBuilder?) {
        if (shouldApply) f()?.let { should = listOf(it) }
    }
}

fun bool(shouldApply: Boolean = true, init: BoolData.() -> Unit): BoolQueryBuilder? {
    if (shouldApply) {
        val params = BoolData().apply(init)
        return BoolQueryBuilder().apply {
            params.must?.filterNotNull()?.forEach { must(it) }
            params.filter?.filterNotNull()?.forEach { filter(it) }
            params.must_not?.filterNotNull()?.forEach { mustNot(it) }
            params.should?.filterNotNull()?.forEach { should(it) }
            params.minimum_should_match?.let { minimumNumberShouldMatch(it) }
            params.boost?.let { boost(it) }
        }
    } else return null
}