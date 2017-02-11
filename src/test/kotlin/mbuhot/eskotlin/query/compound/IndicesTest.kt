/*
 * Copyright (c) 2016. Michael Buhot m.buhot@gmail.com
 */

package mbuhot.eskotlin.query.compound

import mbuhot.eskotlin.query.should_be_null
import mbuhot.eskotlin.query.should_render_as
import mbuhot.eskotlin.query.term.term
import mbuhot.eskotlin.query.util.runIf
import org.junit.Test

/**
 * Created on 3/03/2016

 * @author Michael Buhot (m.buhot@gmail.com)
 */
class IndicesTest {

    @Test
    fun `test indices`() {

        val query = indices {
            indices = listOf("index1", "index2")
            query {
                term { "tag" to "wow" }
            }
            no_match_query {
                term { "tag" to "kow" }
            }
        }

        query should_render_as """
        {
            "indices": {
                "indices": ["index1", "index2"],
                "query": {
                    "term": {
                        "tag": {
                            "value": "wow",
                            "boost": 1.0
                        }
                    }
                },
                "no_match_query": {
                    "term": {
                        "tag": {
                            "value": "kow",
                            "boost": 1.0
                        }
                    }
                },
                "boost": 1.0
            }
        }
        """
    }

    @Test
    fun `test indices with string no_match_query`() {
        val query = indices {
            indices = listOf("index1", "index2")
            query {
                term { "tag" to "wow" }
            }
            no_match_query = "none"
        }

        query should_render_as """
        {
            "indices": {
                "indices": ["index1", "index2"],
                "query": {
                    "term": {
                        "tag": {
                            "value": "wow",
                            "boost": 1.0
                        }
                    }
                },
                "no_match_query": {
                    "match_none": {
                        "boost": 1.0
                    }
                },
                "boost": 1.0
            }
        }
        """
    }

    @Test
    fun `test indices disabled`() {

        val query = runIf(false) {
            indices {
                indices = listOf("index1", "index2")
                query {
                    term { "tag" to "wow" }
                }
                no_match_query {
                    term { "tag" to "kow" }
                }
            }
        }

        query.should_be_null()
    }

    @Test
    fun `test indices disabled parameters`() {
        val query = indices {
            indices = listOf("index1", "index2")
            runIf(true) {
                query {
                    term { "tag" to "wow" }
                }
            }
            runIf(false) {
                query {
                    term { "tag" to "ok" }
                }
            }
            runIf(true) {
                no_match_query {
                    term { "tag" to "ok" }
                }
            }
            runIf(false) {
                no_match_query {
                    term { "tag" to "blah" }
                }
            }
        }

        query should_render_as """
        {
            "indices": {
                "indices": ["index1", "index2"],
                "query": {
                    "term": {
                        "tag": {
                            "value": "wow",
                            "boost": 1.0
                        }
                    }
                },
                "no_match_query": {
                    "term": {
                        "tag": {
                            "value": "ok",
                            "boost": 1.0
                        }
                    }
                },
                "boost": 1.0
            }
        }
        """
    }
}