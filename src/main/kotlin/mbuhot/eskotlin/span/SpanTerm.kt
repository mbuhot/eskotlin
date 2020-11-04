package mbuhot.eskotlin.span

import mbuhot.eskotlin.query.QueryData
import mbuhot.eskotlin.query.initQuery
import org.elasticsearch.index.query.SpanTermQueryBuilder

class SpanTermBlock {
    class SpanTermData(
            var name: String? = null,
            var value: String? = null) : QueryData()

    infix fun String.to(value: String): SpanTermData {
        return SpanTermData(name = this, value = value)
    }
}

fun span_term(init: SpanTermBlock.() -> SpanTermBlock.SpanTermData): SpanTermQueryBuilder {
    val params = SpanTermBlock().init()
    return SpanTermQueryBuilder(params.name, params.value).apply {
        initQuery(params)
    }
}