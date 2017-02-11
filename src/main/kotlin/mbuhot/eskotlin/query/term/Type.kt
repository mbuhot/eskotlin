/*
 * Copyright (c) 2016. Michael Buhot m.buhot@gmail.com
 */

package mbuhot.eskotlin.query.term

import mbuhot.eskotlin.query.QueryData
import mbuhot.eskotlin.query.initQuery
import org.elasticsearch.index.query.TypeQueryBuilder

class TypeData(
        var value: String? = null) : QueryData()

fun type(init: TypeData.() -> Unit): TypeQueryBuilder {
    val params = TypeData().apply(init)
    return TypeQueryBuilder(params.value).apply {
        initQuery(params)
    }
}
