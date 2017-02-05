/*
 * Copyright (c) 2016. Michael Buhot m.buhot@gmail.com
 */

package mbuhot.eskotlin.query.fulltext

import org.elasticsearch.common.unit.Fuzziness
import org.elasticsearch.index.query.MatchQueryBuilder
import org.elasticsearch.index.query.Operator
import org.elasticsearch.index.search.MatchQuery


/**
 * match
 */

class MatchBlock {
    infix fun String.to(init: MatchData.() -> Unit) = MatchData(name = this).apply(init)
    infix fun String.to(query: Any) = MatchData(name = this, query = query)

    data class MatchData(
            var name: String,
            var query: Any? = null,
            var operator: String? = null,
            var analyzer: String? = null,
            var boost: Float? = null,
            var fuzziness: Fuzziness? = null,
            var prefix_length: Int? = null,
            var max_expansions: Int? = null,
            var minimum_should_match: String? = null,
            var fuzzy_rewrite: String? = null,
            var lenient: Boolean? = null,
            var fuzzy_transpositions: Boolean? = null,
            var zero_terms_query: String? = null,
            var cutoff_frequency: Float? = null)
}

fun match(shouldApply: Boolean = true, init: MatchBlock.() -> MatchBlock.MatchData): MatchQueryBuilder? {
    if (shouldApply) {
        val params = MatchBlock().init()
        return MatchQueryBuilder(params.name, params.query).apply {
            params.analyzer?.let { analyzer(it) }
            params.boost?.let { boost(it) }
            params.cutoff_frequency?.let { cutoffFrequency(it) }
            params.fuzziness?.let { fuzziness(it) }
            params.fuzzy_rewrite?.let { fuzzyRewrite(it) }
            params.fuzzy_transpositions?.let { fuzzyTranspositions(it) }
            params.lenient?.let { lenient(it) }
            params.max_expansions?.let { maxExpansions(it) }
            params.minimum_should_match?.let { minimumShouldMatch(it) }
            params.operator?.let { operator(Operator.fromString(it)) }
            params.prefix_length?.let { prefixLength(it) }
            params.zero_terms_query?.let { zeroTermsQuery(MatchQuery.ZeroTermsQuery.valueOf(it.toUpperCase())) }
        }
    } else return null
}