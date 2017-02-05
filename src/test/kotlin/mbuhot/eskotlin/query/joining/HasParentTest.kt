/*
 * Copyright (c) 2016. Michael Buhot m.buhot@gmail.com
 */

package mbuhot.eskotlin.query.joining

import mbuhot.eskotlin.query.should_be_null
import mbuhot.eskotlin.query.should_render_as
import mbuhot.eskotlin.query.term.term
import org.elasticsearch.index.query.InnerHitBuilder
import org.junit.Test

/**
 * Created on 3/03/2016

 * @author Michael Buhot (m.buhot@gmail.com)
 */
class HasParentTest {

    @Test
    fun `test has_parent`() {

        val query = has_parent {
            parent_type = "blog"
            query {
                term {
                    "tag" to "something"
                }
            }
            inner_hits = InnerHitBuilder()
        }

        query should_render_as """
        {
            "has_parent": {
                "query": {
                    "term": {
                        "tag": {
                            "value": "something",
                            "boost": 1.0
                        }
                    }
                },
                "parent_type": "blog",
                "score": false,
                "ignore_unmapped": false,
                "boost": 1.0,
                "inner_hits": {
                    "name": "blog",
                    "from": 0,
                    "size": 3,
                    "version": false,
                    "explain": false,
                    "track_scores": false
                }
            }
        }
        """
    }

    @Test
    fun `test has_parent disabled`() {
        val query = has_parent(false) {
            parent_type = "blog"
            query {
                term {
                    "tag" to "something"
                }
            }
            inner_hits = InnerHitBuilder()
        }

        query.should_be_null()
    }

    @Test
    fun `test has_parent disabled parameters`() {

        val query = has_parent {
            parent_type = "blog"
            query(true) {
                term {
                    "tag" to "something"
                }
            }
            query(false) {
                term {
                    "tag" to "something_else"
                }
            }
            inner_hits = InnerHitBuilder()
        }

        query should_render_as """
        {
            "has_parent": {
                "query": {
                    "term": {
                        "tag": {
                            "value": "something",
                            "boost": 1.0
                        }
                    }
                },
                "parent_type": "blog",
                "score": false,
                "ignore_unmapped": false,
                "boost": 1.0,
                "inner_hits": {
                    "name": "blog",
                    "from": 0,
                    "size": 3,
                    "version": false,
                    "explain": false,
                    "track_scores": false
                }
            }
        }
        """
    }
}