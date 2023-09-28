/*
 * Copyright (c) 2016. Michael Buhot m.buhot@gmail.com
 */

package mbuhot.eskotlin.query.term

import mbuhot.eskotlin.query.QueryData
import mbuhot.eskotlin.query.initQuery
import org.opensearch.index.query.ExistsQueryBuilder

class ExistsData(var field: String? = null) : QueryData()

fun exists(init: ExistsData.() -> Unit): ExistsQueryBuilder {
    val params = ExistsData().apply(init)
    return ExistsQueryBuilder(params.field).apply {
        initQuery(params)
    }
}
