/*
 * Copyright (c) 2016. Michael Buhot m.buhot@gmail.com
 */

package mbuhot.eskotlin.query.term

import mbuhot.eskotlin.query.should_be_null
import mbuhot.eskotlin.query.should_render_as
import org.junit.Test

class TermTest {

    @Test
    fun `test simple term`() {
        val query = term {
            "user" to "Kimchy"
        }

        query should_render_as """
            {
                "term" : {
                    "user" : {
                        "value": "Kimchy",
                        "boost": 1.0
                    }
                }
            }
            """
    }

    @Test
    fun `test term disabled`() {
        val query = term(false) {
            "user" to "Kimchy"
        }

        query.should_be_null()
    }

    @Test
    fun `test term with boost and name`() {
        val query = term {
            "status" to {
                value = "urgent"
                boost = 2.0f
            }
        }
        query should_render_as """
            {
                "term" : {
                    "status" : {
                        "value" : "urgent",
                        "boost" : 2.0
                    }
                }
            }
            """
    }
}

