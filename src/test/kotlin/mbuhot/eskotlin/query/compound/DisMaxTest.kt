/*
 * Copyright (c) 2016. Michael Buhot m.buhot@gmail.com
 */

package mbuhot.eskotlin.query.compound

import mbuhot.eskotlin.query.should_be_null
import mbuhot.eskotlin.query.should_render_as
import mbuhot.eskotlin.query.term.term
import org.junit.Test

/**
 * Created on 3/03/2016

 * @author Michael Buhot (m.buhot@gmail.com)
 */
class DisMaxTest {

    @Test
    fun `test dis_max`() {
        val query = dis_max {
            tie_breaker = 0.7f
            boost = 1.2f
            queries = listOf(
                term { "age" to "34" },
                term { "age" to "35" }
            )
        }

        query should_render_as """
        {
            "dis_max": {
                "tie_breaker": 0.7,
                "queries": [{
                    "term": {
                        "age": {
                            "value": "34",
                            "boost": 1.0
                        }
                    }
                }, {
                    "term": {
                        "age": {
                            "value": "35",
                            "boost": 1.0
                        }
                    }
                }],
                "boost": 1.2
            }
        }
        """
    }

    @Test
    fun `test dis_max disabled`() {
        val query = dis_max(false) {
            tie_breaker = 0.7f
            boost = 1.2f
            queries = listOf(
                    term { "age" to "34" },
                    term { "age" to "35" }
            )
        }

        query.should_be_null()
    }

    @Test
    fun `test dis_max disabled queries`() {
        val query = dis_max {
            tie_breaker = 0.7f
            boost = 1.2f
            queries = listOf(
                    term { "age" to "34" },
                    term(false) { "age" to "35" }
            )
        }

        query should_render_as """
        {
            "dis_max": {
                "tie_breaker": 0.7,
                "queries": [{
                    "term": {
                        "age": {
                            "value": "34",
                            "boost": 1.0
                        }
                    }
                }],
                "boost": 1.2
            }
        }
        """
    }
}