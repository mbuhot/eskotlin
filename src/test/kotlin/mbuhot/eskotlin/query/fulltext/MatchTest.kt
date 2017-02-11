/*
 * Copyright (c) 2016. Michael Buhot m.buhot@gmail.com
 */

package mbuhot.eskotlin.query.fulltext

import mbuhot.eskotlin.query.should_be_null
import mbuhot.eskotlin.query.should_render_as
import mbuhot.eskotlin.query.term.boost
import mbuhot.eskotlin.query.term.match_all
import mbuhot.eskotlin.query.util.runIf
import org.junit.Test

/**
 * Created on 2/03/2016

 * @author Michael Buhot (m.buhot@gmail.com)
 */


class MatchTest {
    @Test
    fun `test match_all`() {
        val query = match_all {
            boost = 1.2f
        }
        query should_render_as """{ "match_all": { "boost" : 1.2 }}"""
    }

    @Test
    fun `test match_all disabled`() {
        val query = runIf(false) {
            match_all {
                boost = 1.2f
            }
        }
        query.should_be_null()
    }

    @Test
    fun `test simple match`() {
        val query = match { "message" to "this is a test" }
        query should_render_as """
            {
                "match": {
                    "message": {
                        "query": "this is a test",
                        "operator": "OR",
                        "prefix_length": 0,
                        "max_expansions": 50,
                        "fuzzy_transpositions": true,
                        "lenient": false,
                        "zero_terms_query": "NONE",
                        "boost": 1.0
                    }
                }
            }
            """
    }

    @Test
    fun `test match disabled`() {
        val query = runIf(false) {
            match { "message" to "this is a test" }
        }
        query.should_be_null()
    }

    @Test
    fun `test match with query and operator`() {

        val query = match {
            "message" to {
                query = "this is a test"
                operator = "and"
            }
        }

        query should_render_as """
            {
                "match": {
                    "message": {
                        "query": "this is a test",
                        "operator": "AND",
                        "prefix_length": 0,
                        "max_expansions": 50,
                        "fuzzy_transpositions": true,
                        "lenient": false,
                        "zero_terms_query": "NONE",
                        "boost": 1.0
                    }
                }
            }
            """
    }

    @Test
    fun `test match zero terms query`() {

        val query = match {
            "message" to {
                query = "to be or not to be"
                operator = "and"
                zero_terms_query = "all"
            }
        }
        query should_render_as """
            {
                "match": {
                    "message": {
                        "query": "to be or not to be",
                        "operator": "AND",
                        "prefix_length": 0,
                        "max_expansions": 50,
                        "fuzzy_transpositions": true,
                        "lenient": false,
                        "zero_terms_query": "ALL",
                        "boost": 1.0
                    }
                }
            }
            """
    }


    @Test
    fun `test match cutoff frequency`() {
        val query = match {
            "message" to {
                query = "to be or not to be"
                cutoff_frequency = 0.001f
            }
        }

        query should_render_as """
            {
                "match": {
                    "message": {
                        "query": "to be or not to be",
                        "operator": "OR",
                        "prefix_length": 0,
                        "max_expansions": 50,
                        "fuzzy_transpositions": true,
                        "lenient": false,
                        "zero_terms_query": "NONE",
                        "cutoff_frequency": 0.001,
                        "boost": 1.0
                    }
                }
            }
            """
    }

    @Test
    fun `test match type phrase`() {
        val query = match {
            "message" to {
                query = "this is a test"
            }
        }

        query should_render_as """
            {
                "match": {
                    "message": {
                        "query": "this is a test",
                        "operator": "OR",
                        "prefix_length": 0,
                        "max_expansions": 50,
                        "fuzzy_transpositions": true,
                        "lenient": false,
                        "zero_terms_query": "NONE",
                        "boost": 1.0
                    }
                }
            }
            """
    }

}