/*
 * Copyright (c) 2016. Ryan Murfitt rmurfitt@gmail.com
 */

package mbuhot.eskotlin.query.fulltext

import org.elasticsearch.index.query.MatchPhraseQueryBuilder


/**
 * match_phrase
 */

class MatchPhraseBlock {
    infix fun String.to(init: MatchPhraseData.() -> Unit) = MatchPhraseData(name = this).apply(init)
    infix fun String.to(query: Any) = MatchPhraseData(name = this, query = query)

    data class MatchPhraseData(
            var name: String,
            var query: Any? = null,
            var analyzer: String? = null,
            var slop: Int? = null)
}

fun match_phrase(init: MatchPhraseBlock.() -> MatchPhraseBlock.MatchPhraseData): MatchPhraseQueryBuilder {
    val params = MatchPhraseBlock().init()
    return MatchPhraseQueryBuilder(params.name, params.query).apply {
        params.analyzer?.let { analyzer(it) }
        params.slop?.let { slop(it) }
    }
}
