/*
 * Copyright (c) 2016. Michael Buhot m.buhot@gmail.com
 */

package mbuhot.eskotlin.query.term

import mbuhot.eskotlin.query.QueryData
import mbuhot.eskotlin.query.initQuery
import org.elasticsearch.index.query.ExistsQueryBuilder

class ExistsData(var field: String? = null) : QueryData()

fun exists(shouldApply: Boolean = true, init: ExistsData.() -> Unit): ExistsQueryBuilder? {
    if (shouldApply) {
        val params = ExistsData().apply(init)
        return ExistsQueryBuilder(params.field).apply {
            initQuery(params)
        }
    } else return null
}
