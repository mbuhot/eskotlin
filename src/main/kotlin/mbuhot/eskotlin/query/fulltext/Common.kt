/*
 * Copyright (c) 2016. Michael Buhot m.buhot@gmail.com
 */

package mbuhot.eskotlin.query.fulltext

import org.elasticsearch.index.query.CommonTermsQueryBuilder
import org.elasticsearch.index.query.Operator


/**
 * common
 */
class CommonBlock {
    infix fun String.to(init: CommonData.() -> Unit): CommonData {
        return CommonData(name = this).apply(init)
    }

    data class CommonData(
            var name: String,
            var query: Any? = null,
            var high_freq_operator: String? = null,
            var low_freq_operator: String? = null,
            var analyzer: String? = null,
            var boost: Float? = null,
            var disable_coord: Boolean? = null,
            var cutoff_frequency: Float? = null,
            val minimum_should_match: MinimumShouldMatchData = MinimumShouldMatchData()) {

        fun minimum_should_match(init: MinimumShouldMatchData.() -> Unit) {
            this.minimum_should_match.init()
        }

        infix fun MinimumShouldMatchData.to(numOrStr: Any) {
            this.low_freq = numOrStr
        }
    }

    data class MinimumShouldMatchData(var low_freq: Any? = null, var high_freq: Any? = null)
}

fun common(init: CommonBlock.() -> CommonBlock.CommonData): CommonTermsQueryBuilder {
    val params = CommonBlock().init()
    return CommonTermsQueryBuilder(params.name, params.query).apply {
        params.high_freq_operator?.let { highFreqOperator(Operator.fromString(it)) }
        params.low_freq_operator?.let { lowFreqOperator(Operator.fromString(it)) }
        params.analyzer?.let { analyzer(it) }
        params.boost?.let { boost(it) }
        params.disable_coord?.let { disableCoord(it) }
        params.cutoff_frequency?.let { cutoffFrequency(it) }
        params.minimum_should_match.low_freq?.toString()?.let { lowFreqMinimumShouldMatch(it) }
        params.minimum_should_match.high_freq?.toString()?.let { highFreqMinimumShouldMatch(it) }
    }
}
