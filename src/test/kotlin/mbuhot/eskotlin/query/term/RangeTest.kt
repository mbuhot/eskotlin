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
class RangeTest {

    @Test
    fun `test range`() {
        val query = range {
            "age" to {
                gte = 10
                lte = 20
                boost = 2.0f
            }
        }
        query should_render_as """
        {
            "range" : {
                "age" : {
                    "from" : 10,
                    "to" : 20,
                    "include_lower" : true,
                    "include_upper" : true,
                    "boost" : 2.0
                }
            }
        }
        """
    }

    @Test
    fun `test range disabled`() {
        val query = range(false) {
            "age" to {
                gte = 10
                lte = 20
                boost = 2.0f
            }
        }
        query.should_be_null()
    }

    @Test
    fun `test range with date format and time_zone`() {
        val query = range {
            "born" to {
                gt = "01/01/2012"
                lt = "2013"
                format = "dd/MM/yyyy||yyyy"
                time_zone = "+01:00"
            }
        }

        query should_render_as """
        {
            "range" : {
                "born" : {
                    "from" : "01/01/2012",
                    "to" : "2013",
                    "include_lower" : false,
                    "include_upper" : false,
                    "time_zone" : "+01:00",
                    "format" : "dd/MM/yyyy||yyyy",
                    "boost": 1.0
                }
            }
        }
        """
    }
}