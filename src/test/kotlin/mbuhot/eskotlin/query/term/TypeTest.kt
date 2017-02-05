/*
 * Copyright (c) 2016. Michael Buhot m.buhot@gmail.com
 */

package mbuhot.eskotlin.query.term

import mbuhot.eskotlin.query.should_be_null
import mbuhot.eskotlin.query.should_render_as
import org.junit.Test

/**
 * Created on 3/03/2016

 * @author Michael Buhot (m.buhot@gmail.com)
 */
class TypeTest {
    @Test
    fun `test type`() {
        val query = type {
            value = "my_type"
        }
        query should_render_as """
        {
            "type": {
                "value" : "my_type",
                "boost" : 1.0
            }
        }
        """
    }

    @Test
    fun `test type disabled`() {
        val query = type(false) {
            value = "my_type"
        }
        query.should_be_null()
    }

    @Test
    fun `test type with boost`() {
        val query = type {
            value = "my_type"
            boost = 2.0f
        }
        query should_render_as """
        {
            "type": {
                "value" : "my_type",
                "boost" : 2.0
            }
        }
        """
    }
}