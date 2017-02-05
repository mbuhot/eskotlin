/*
 * Copyright (c) 2016. Michael Buhot m.buhot@gmail.com
 */

package mbuhot.eskotlin.query.fulltext

import org.elasticsearch.common.unit.Fuzziness
import org.elasticsearch.index.query.MultiMatchQueryBuilder
import org.elasticsearch.index.query.Operator
import org.elasticsearch.index.search.MatchQuery

data class MultiMatchData(
        var query: Any? = null,
        var fields: List<String>? = null,
        var type: String? = null,
        var operator: String? = null,
        var analyzer: String? = null,
        var boost: Float? = null,
        var slop: Int? = null,
        var fuzziness: Fuzziness? = null,
        var prefix_length: Int? = null,
        var max_expansions: Int? = null,
        var minimum_should_match: String? = null,
        var fuzzy_rewrite: String? = null,
        var tie_breaker: Float? = null,
        var lenient: Boolean? = null,
        var cutoff_frequency: Float? = null,
        var zero_terms_query: MatchQuery.ZeroTermsQuery? = null)

fun multi_match(shouldApply: Boolean = true, init: MultiMatchData.() -> Unit): MultiMatchQueryBuilder? {
    if (shouldApply) {
        val params = MultiMatchData().apply(init)
        return MultiMatchQueryBuilder(params.query, *params.fields!!.toTypedArray()).apply {
            params.type?.let { type(it) }
            params.operator?.let { operator(Operator.fromString(it)) }
            params.analyzer?.let { analyzer(it) }
            params.boost?.let { boost(it) }
            params.slop?.let { slop(it) }
            params.fuzziness?.let { fuzziness(it) }
            params.prefix_length?.let { prefixLength(it) }
            params.max_expansions?.let { maxExpansions(it) }
            params.minimum_should_match?.let { minimumShouldMatch(it) }
            params.fuzzy_rewrite?.let { fuzzyRewrite(it) }
            params.tie_breaker?.let { tieBreaker(it) }
            params.lenient?.let { lenient(it) }
            params.cutoff_frequency?.let { cutoffFrequency(it) }
            params.zero_terms_query?.let { zeroTermsQuery(it) }
        }
    } else return null
}