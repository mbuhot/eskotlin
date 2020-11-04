package mbuhot.eskotlin.span

import mbuhot.eskotlin.query.should_render_as
import org.junit.Test

class SpanTermTest {
    @Test
    fun `span term test`() {
        val query = span_term {
            "field" to "value1"
        }

        query should_render_as """
            {
                "span_term": {
                    "field": {
                        "value": "value1",
                        "boost": 1.0
                    }
                }
            }
        """.trimIndent()
    }
}