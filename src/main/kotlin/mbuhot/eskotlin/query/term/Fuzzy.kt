/*
 * Copyright (c) 2016. Michael Buhot m.buhot@gmail.com
 */

package mbuhot.eskotlin.query.term

import mbuhot.eskotlin.query.QueryData
import mbuhot.eskotlin.query.initQuery
import org.elasticsearch.common.unit.Fuzziness
import org.elasticsearch.index.query.FuzzyQueryBuilder

class FuzzyBlock {
    class FuzzyData(
            val name: String,
            var value: Any? = null,
            var fuzziness: Fuzziness? = null,
            var prefix_length: Int? = null,
            var max_expansions: Int? = null) : QueryData()

    infix fun String.to(value: Any) = FuzzyData(name = this, value = value)

    infix fun String.to(init: FuzzyData.() -> Unit): FuzzyData =
            FuzzyData(name = this).apply(init)
}

fun fuzzy(shouldApply: Boolean = true, init: FuzzyBlock.() -> FuzzyBlock.FuzzyData): FuzzyQueryBuilder? {
    if (shouldApply) {
        val params = FuzzyBlock().init()
        return FuzzyQueryBuilder(params.name, params.value).apply {
            initQuery(params)
            params.fuzziness?.let { fuzziness(it) }
            params.prefix_length?.let { prefixLength(it) }
            params.max_expansions?.let { maxExpansions(it) }
        }
    } else return null
}

