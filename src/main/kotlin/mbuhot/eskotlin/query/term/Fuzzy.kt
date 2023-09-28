/*
 * Copyright (c) 2016. Michael Buhot m.buhot@gmail.com
 */

package mbuhot.eskotlin.query.term

import mbuhot.eskotlin.query.QueryData
import mbuhot.eskotlin.query.initQuery
import org.opensearch.common.unit.Fuzziness
import org.opensearch.index.query.FuzzyQueryBuilder

class FuzzyBlock {
    class FuzzyData(
            val name: String,
            var value: Any? = null,
            var fuzziness: Fuzziness? = null,
            var prefix_length: Int? = null,
            var max_expansions: Int? = null,
            var transpositions: Boolean? = null) : QueryData()

    infix fun String.to(value: Any) = FuzzyData(name = this, value = value)

    @Deprecated(message = "Use invoke operator instead.", replaceWith = ReplaceWith("invoke(init)"))
    infix fun String.to(init: FuzzyData.() -> Unit) = this.invoke(init)

    operator fun String.invoke(init: FuzzyData.() -> Unit) = FuzzyData(name = this).apply(init)
}

@Suppress("DEPRECATION")
fun fuzzy(init: FuzzyBlock.() -> FuzzyBlock.FuzzyData): FuzzyQueryBuilder {
    val params = FuzzyBlock().init()
    return FuzzyQueryBuilder(params.name, params.value).apply {
        initQuery(params)
        params.fuzziness?.let { fuzziness(it) }
        params.prefix_length?.let { prefixLength(it) }
        params.max_expansions?.let { maxExpansions(it) }
        transpositions(params.transpositions ?: false)
    }
}

