package mbuhot.eskotlin.span

import org.elasticsearch.index.query.SpanNearQueryBuilder
import org.elasticsearch.index.query.SpanQueryBuilder

data class SpanNearData(
        var clauses: List<SpanQueryBuilder>,
        var slop: Int = 0,
        var in_order: Boolean = false
) {

    fun clauses(f: () -> SpanQueryBuilder) {
        clauses = listOf(f())
    }
}

fun span_near(init: SpanNearData.() -> Unit): SpanNearQueryBuilder? {
    val params = SpanNearData(listOf()).apply(init)

    if (params.clauses.isEmpty())
        throw IllegalArgumentException("[span_near] must include at least one clause")

    return SpanNearQueryBuilder(params.clauses.first(), params.slop).apply {
        if (params.clauses.size > 1) {
            params.clauses.forEachIndexed { index, spanQueryBuilder ->
                if (index > 0)
                    addClause(spanQueryBuilder)
            }
        }

        inOrder(params.in_order)
    }
}