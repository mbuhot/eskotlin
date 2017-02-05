/*
 * Copyright (c) 2016. Michael Buhot m.buhot@gmail.com
 */

package mbuhot.eskotlin.query.compound

import org.elasticsearch.index.query.DisMaxQueryBuilder
import org.elasticsearch.index.query.QueryBuilder

data class DisMaxData(
        var tie_breaker: Float? = null,
        var boost: Float? = null,
        var queries: List<QueryBuilder?>? = null)

fun dis_max(shouldApply: Boolean = true, init: DisMaxData.() -> Unit): DisMaxQueryBuilder? {
    if (shouldApply) {
        val params = DisMaxData().apply(init)
        return DisMaxQueryBuilder().apply {
            params.tie_breaker?.let { tieBreaker(it) }
            params.boost?.let { boost(it) }
            params.queries?.filterNotNull()?.forEach { add(it) }
        }
    } else return null
}
