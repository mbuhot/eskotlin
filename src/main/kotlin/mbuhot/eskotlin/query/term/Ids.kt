/*
 * Copyright (c) 2016. Michael Buhot m.buhot@gmail.com
 */

package mbuhot.eskotlin.query.term

import mbuhot.eskotlin.query.QueryData
import mbuhot.eskotlin.query.initQuery
import org.elasticsearch.index.query.IdsQueryBuilder

class IdsData(
        var types: List<String> = emptyList(),
        var values: List<String> = emptyList()) : QueryData() {

    var type: String
        get() = types[0]
        set(value) {
            types = listOf(value)
        }
}


fun ids(init: IdsData.() -> Unit): IdsQueryBuilder {
    val params = IdsData().apply(init)
    return IdsQueryBuilder(*params.types.toTypedArray()).apply {
        initQuery(params)
        addIds(*params.values.toTypedArray())
    }
}