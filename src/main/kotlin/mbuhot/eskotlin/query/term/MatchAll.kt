/*
 * Copyright (c) 2016. Michael Buhot m.buhot@gmail.com
 */

package mbuhot.eskotlin.query.term

import org.elasticsearch.index.query.MatchAllQueryBuilder


/**
 * match_all
 */
fun match_all(shouldApply: Boolean = true, init: MatchAllQueryBuilder.() -> Unit) =
        if (shouldApply) MatchAllQueryBuilder().apply(init)
        else null

var MatchAllQueryBuilder.boost: Float
    get() = error("write-only property")
    set(value) {
        this.boost(value)
    }