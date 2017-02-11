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
class ExistsTest {
    @Test
    fun `test exists`() {
        val query = exists {
            field = "user"
        }

        query should_render_as """
        {
            "exists" : {
                "field" : "user",
                "boost" : 1.0
            }
        }
        """
    }

    @Test
    fun `test exists disabled`() {
        val query = runIf(false) {
            exists {
                field = "user"
            }
        }

        query.should_be_null()
    }
}