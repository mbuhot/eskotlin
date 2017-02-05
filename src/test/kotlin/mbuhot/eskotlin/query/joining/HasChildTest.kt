/*
 * Copyright (c) 2016. Michael Buhot m.buhot@gmail.com
 */

package mbuhot.eskotlin.query.joining

import mbuhot.eskotlin.query.should_be_null
import mbuhot.eskotlin.query.should_render_as
import mbuhot.eskotlin.query.term.term
import org.apache.lucene.search.join.ScoreMode
import org.elasticsearch.index.query.InnerHitBuilder
import org.junit.Test

/**
 * Created on 3/03/2016

 * @author Michael Buhot (m.buhot@gmail.com)
 */
class HasChildTest {

    @Test
    fun `test has_child`() {
        val query = has_child {
            type = "blog_tag"
            score_mode = ScoreMode.Total
            min_children = 2
            max_children = 10
            query {
                term {
                    "tag" to "something"
                }
            }
            inner_hits = InnerHitBuilder()
        }
        query should_render_as """
        {
            "has_child": {
                "query": {
                    "term": {
                        "tag": {
                            "value": "something",
                            "boost": 1.0
                        }
                    }
                },
                "type": "blog_tag",
                "score_mode": "sum",
                "min_children": 2,
                "max_children": 10,
                "ignore_unmapped": false,
                "boost": 1.0,
                "inner_hits": {
                    "name": "blog_tag",
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
    fun `test has_child disabled`() {
        val query = has_child(false) {
            type = "blog_tag"
            score_mode = ScoreMode.Total
            min_children = 2
            max_children = 10
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
    fun `test has_child disabled parameters`() {
        val query = has_child {
            type = "blog_tag"
            score_mode = ScoreMode.Total
            min_children = 2
            max_children = 10
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
            "has_child": {
                "query": {
                    "term": {
                        "tag": {
                            "value": "something",
                            "boost": 1.0
                        }
                    }
                },
                "type": "blog_tag",
                "score_mode": "sum",
                "min_children": 2,
                "max_children": 10,
                "ignore_unmapped": false,
                "boost": 1.0,
                "inner_hits": {
                    "name": "blog_tag",
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