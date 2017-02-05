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


class MatchPhrasePrefixTest {

    @Test
    fun `test match_phrase_prefix`() {
        val query = match_phrase_prefix {
            "message" to "this is a test"
        }

        query should_render_as """
            {
                "match_phrase_prefix" : {
                    "message" : {
                        "query" : "this is a test",
                        "slop":0,
                        "max_expansions":50,
                        "boost":1.0
                    }
                }
            }
            """
    }

    @Test
    fun `test match_phrase_prefix disabled`() {
        val query = match_phrase_prefix(false) {
            "message" to "this is a test"
        }

        query.should_be_null()
    }

    @Test
    fun `test match_phrase_prefix with max_expansions`() {
        val query = match_phrase_prefix {
            "message" to {
                query = "this is a test"
                max_expansions = 10
            }
        }

        query should_render_as """
            {
                "match_phrase_prefix" : {
                    "message" : {
                        "query" : "this is a test",
                        "slop":0,
                        "max_expansions":10,
                        "boost":1.0
                    }
                }
            }
            """
    }

    @Test
    fun `test match_phrase_prefix with max_expansions and slop`() {
        val query = match_phrase_prefix {
            "message" to {
                query = "this is a test"
                max_expansions = 10
                slop = 2
            }
        }

        query should_render_as """
            {
                "match_phrase_prefix" : {
                    "message" : {
                        "query" : "this is a test",
                        "slop":2,
                        "max_expansions":10,
                        "boost":1.0
                    }
                }
            }
            """
    }
}