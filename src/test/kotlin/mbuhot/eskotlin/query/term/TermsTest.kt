/*
 * Copyright (c) 2016. Michael Buhot m.buhot@gmail.com
 */

package mbuhot.eskotlin.query.term

import mbuhot.eskotlin.query.should_be_null
import mbuhot.eskotlin.query.should_render_as
import mbuhot.eskotlin.query.util.runIf
import org.junit.Test

/**
 * Created on 3/03/2016

 * @author Michael Buhot (m.buhot@gmail.com)
 */
class TermsTest {

    @Test
    fun `test terms`() {
        val query = terms {
            "user" to listOf("kimchy", "elasticsearch")
        }

        query should_render_as """
            {
                "terms" : {
                    "user" : ["kimchy", "elasticsearch"],
                    "boost": 1.0
                }
            }
            """
    }

    @Test
    fun `test terms disabled`() {
        val query = runIf(false) {
            terms {
                "user" to listOf("kimchy", "elasticsearch")
            }
        }

        query.should_be_null()
    }
}