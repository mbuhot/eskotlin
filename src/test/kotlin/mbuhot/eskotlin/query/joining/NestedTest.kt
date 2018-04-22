/*
 * Copyright (c) 2016. Michael Buhot m.buhot@gmail.com
 */

package mbuhot.eskotlin.query.joining

import mbuhot.eskotlin.query.compound.bool
import mbuhot.eskotlin.query.fulltext.match
import mbuhot.eskotlin.query.should_render_as
import mbuhot.eskotlin.query.term.range
import org.apache.lucene.search.join.ScoreMode
import org.junit.Test

/**
 * Created on 3/03/2016

 * @author Michael Buhot (m.buhot@gmail.com)
 */
class NestedTest {

    @Test
    fun `test nested`() {
        val query = nested {
            path = "obj1"
            score_mode = ScoreMode.Avg
            query {
                bool {
                    must {
                        match { "obj1.name" to "blue" }
                        range { "obj1.count" { gt = 5 } }
                    }
                }
            }
        }

        query should_render_as """
        {
            "nested" : {
                "query" : {
                    "bool" : {
                        "must" : [
                            {
                                "match" : {
                                    "obj1.name" : { 
                                        "query": "blue",
                                        "operator":"OR",
                                        "prefix_length":0,
                                        "max_expansions":50,
                                        "fuzzy_transpositions":true,
                                        "lenient":false,
                                        "zero_terms_query":"NONE",
                                        "boost": 1.0
                                    } 
                                }
                            },
                            {
                                "range" : {
                                    "obj1.count" : {
                                        "from" : 5,
                                        "to" : null,
                                        "include_lower" : false,
                                        "include_upper" : true,
                                        "boost": 1.0
                                    }
                                }
                            }
                        ],
                        "disable_coord":false,
                        "adjust_pure_negative":true,
                        "boost":1.0
                    }
                },
                "path" : "obj1",
                "ignore_unmapped":false,
                "score_mode" : "avg",
                "boost": 1.0
            }
        }
        """
    }
}