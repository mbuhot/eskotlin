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
class PrefixTest {
    @Test
    fun `test prefix`() {
        val query = prefix {
            "user" to "ki"
        }

        query should_render_as """
        {
            "prefix" : {
                "user" : {
                    "value": "ki",
                    "boost": 1.0
                }
            }
        }"""
    }

    @Test
    fun `test prefix disabled`() {
        val query = runIf(false) {
            prefix {
                "user" to "ki"
            }
        }

        query.should_be_null()
    }

    @Test
    fun `test prefix with boost`() {
        val query = prefix {
            "user" to {
                prefix = "ki"
                boost = 2.0f
            }
        }
        query should_render_as """
        {
            "prefix" : {
                "user" : {
                    "value" : "ki",
                    "boost" : 2.0
                }
            }
        }
        """
    }
}