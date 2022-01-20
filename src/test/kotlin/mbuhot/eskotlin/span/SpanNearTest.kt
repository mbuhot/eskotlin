package mbuhot.eskotlin.span

import mbuhot.eskotlin.query.should_render_as
import org.junit.Test

class SpanNearTest {
    @Test(expected = IllegalArgumentException::class)
    fun `span term should throw exception when clauses is empty`() {
        span_near {
            slop = 12
            in_order = true
        }
    }

    @Test
    fun `span term near test should`() {
        val spanNear = span_near {
            slop = 12
            in_order = true
            clauses = listOf(
                    span_term { "field" to "value1" },
                    span_term { "field" to "value2" },
                    span_term { "field" to "value3" }
            )
        }

        spanNear should_render_as """
            {
                "span_near": {
                    "clauses": [
                        { "span_term": { "field": { "value": "value1", "boost": 1.0 } } },
                        { "span_term": { "field": { "value": "value2", "boost": 1.0 } } },
                        { "span_term": { "field": { "value": "value3", "boost": 1.0 } } }
                    ],
                    "slop": 12,
                    "in_order": true,
                    "boost": 1.0
                }
            }
        """.trimIndent()
    }
}

