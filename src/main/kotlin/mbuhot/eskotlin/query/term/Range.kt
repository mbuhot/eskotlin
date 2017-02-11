/*
 * Copyright (c) 2016. Michael Buhot m.buhot@gmail.com
 */

package mbuhot.eskotlin.query.term

import mbuhot.eskotlin.query.QueryData
import mbuhot.eskotlin.query.initQuery
import org.elasticsearch.index.query.RangeQueryBuilder

class RangeBlock {
     class RangeData(
        var name: String,
        var from: Any? = null,
        var to: Any? = null,
        var include_upper: Boolean? = null,
        var include_lower: Boolean? = null,
        var format: String? = null,
        var time_zone: String? = null) : QueryData() {

        var gte: Any?
            get() = this.from
            set(value) {
                from = value
                include_lower = true
            }

        var gt: Any?
            get() = this.from
            set(value) {
                from = value
                include_lower = false
            }

        var lte: Any?
            get() = this.to
            set(value) {
                to = value
                include_upper = true
            }

        var lt: Any?
            get() = this.to
            set(value) {
                to = value
                include_upper = false
            }
    }

    infix fun String.to(init: RangeData.() -> Unit): RangeData {
        return RangeData(name = this).apply(init)
    }
}

fun range(init: RangeBlock.() -> RangeBlock.RangeData): RangeQueryBuilder {
        val params = RangeBlock().init()
        return RangeQueryBuilder(params.name).apply {
            initQuery(params)
            params.from?.let { from(it) }
            params.to?.let { to(it) }
            params.include_lower?.let { includeLower(it) }
            params.include_upper?.let { includeUpper(it) }
            params.boost?.let { boost(it) }
            params.format?.let { format(it) }
            params.time_zone?.let { timeZone(it) }
        }
}