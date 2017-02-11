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
class WildcardTest {
    @Test
    fun `test wildcard`() {
        val query = wildcard {
            "user" to "ki*y"
        }

        query should_render_as """
         {
            "wildcard" : {
                "user" : {
                    "wildcard" : "ki*y",
                    "boost" : 1.0
                }
            }
         }"""
    }

    @Test
    fun `test wildcard disabled`() {
        val query = runIf(false) {
            wildcard {
                "user" to "ki*y"
            }
        }

        query.should_be_null()
    }

    @Test
    fun `test wildcard with boost`() {
        val query = wildcard {
            "user" to {
                wildcard = "ki*y"
                boost = 2.0f
            }
        }

        query should_render_as """
        {
            "wildcard" : {
                "user" : {
                    "wildcard" : "ki*y",
                    "boost" : 2.0
                }
            }
        }
        """
    }
}