/*
 * Copyright (c) 2016. Michael Buhot m.buhot@gmail.com
 */

package mbuhot.eskotlin.query.fulltext

import mbuhot.eskotlin.query.should_be_null
import mbuhot.eskotlin.query.should_render_as
import org.junit.Test

/**
 * Created on 2/03/2016

 * @author Michael Buhot (m.buhot@gmail.com)
 */


class MatchPhraseTest {

    @Test
    fun `test match_phrase`() {
        val query = match_phrase {
            "message" to "this is a test"
        }

        query should_render_as """
            {
                "match_phrase" : {
                    "message" : {
                        "query" : "this is a test",
                        "slop":0,
                        "boost":1.0
                    }
                }
            }
            """
    }

    @Test
    fun `test match_phrase disabled`() {
        val query = match_phrase(false) {
            "message" to "this is a test"
        }

        query.should_be_null()
    }

    @Test
    fun `test match_phrase with analyzer`() {
        val query = match_phrase {
            "message" to {
                query = "this is a test"
                analyzer = "my_analyzer"
            }
        }
        query should_render_as """
            {
                "match_phrase" : {
                    "message" : {
                        "query" : "this is a test",
                        "analyzer" : "my_analyzer",
                        "slop":0,
                        "boost":1.0
                    }
                }
            }
            """
    }
}