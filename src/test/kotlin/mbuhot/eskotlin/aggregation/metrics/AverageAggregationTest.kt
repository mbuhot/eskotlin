package mbuhot.eskotlin.aggregation.metrics

import mbuhot.eskotlin.query.should_render_as
import org.junit.Test

class AverageAggregationTest {
    @Test
    fun `test average aggregation`() {
        val agg = averageAggregation {
            name = "avg_grade"
            field = "grade"
        }

        agg should_render_as """
            {
                "avg_grade" : { "avg" : { "field" : "grade" } }
            }
        """.trimIndent()
    }

    @Test
    fun `test average aggregation with missing value`() {
        val agg = averageAggregation {
            name = "avg_grade"
            field = "grade"
            missing = 10
        }

        agg should_render_as """
            {
                "avg_grade" : { "avg": { "field" : "grade", "missing" : 10 } }
            }
        """.trimIndent()
    }
}
