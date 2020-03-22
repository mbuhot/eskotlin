package mbuhot.eskotlin.aggregation.metrics

import org.elasticsearch.search.aggregations.AggregationBuilder
import org.elasticsearch.search.aggregations.AggregationBuilders
import org.elasticsearch.search.aggregations.metrics.avg.AvgAggregationBuilder

data class AverageAggregationData(
        var name: String? = null,
        var field: String? = null,
        var missing: Int? = null
)

fun averageAggregation(init: AverageAggregationData.() -> Unit): AggregationBuilder {
    val params = AverageAggregationData().apply(init)

    val agg = AggregationBuilders
            .avg(params.name)
            .field(params.field)

    return if (params.missing != null) {
        agg.missing(params.missing)
    } else {
        agg
    }
}
