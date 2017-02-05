/*
 * Copyright (c) 2016. Michael Buhot m.buhot@gmail.com
 */

package mbuhot.eskotlin.query.term

import mbuhot.eskotlin.query.QueryData
import mbuhot.eskotlin.query.initQuery
import org.elasticsearch.index.query.TypeQueryBuilder

class TypeData(
        var value: String? = null) : QueryData()

fun type(shouldApply: Boolean = true, init: TypeData.() -> Unit): TypeQueryBuilder? {
    if (shouldApply) {
        val params = TypeData().apply(init)
        return TypeQueryBuilder(params.value).apply {
            initQuery(params)
        }
    } else return null
}
