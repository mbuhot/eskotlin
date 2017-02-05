/*
 * Copyright (c) 2016. Michael Buhot m.buhot@gmail.com
 */

package mbuhot.eskotlin.query.compound

import mbuhot.eskotlin.query.should_be_null
import mbuhot.eskotlin.query.should_render_as
import mbuhot.eskotlin.query.term.match_all
import mbuhot.eskotlin.query.term.range
import mbuhot.eskotlin.query.term.term
import org.junit.Test

/**
 * Created on 3/03/2016

 * @author Michael Buhot (m.buhot@gmail.com)
 */
class BoolTest {
    @Test
    fun `test bool single should`() {
        val query = bool {
            should {
                match_all { }
            }
        }

        query should_render_as """
        {
            "bool" : {
                "should" : [{
                    "match_all" : {
                        "boost": 1.0
                    }
                }],
                "disable_coord":false,
                "adjust_pure_negative":true,
                "boost":1.0
            }
        }
        """
    }

    @Test
    fun `test bool disabled`() {
        val query = bool(false) {
            should {
                match_all { }
            }
        }

        query.should_be_null()
    }

    @Test
    fun `test bool disabled parameters`() {
        val query = bool {
            must {
                term { "user" to "kimchy" }
            }
            filter(true) {
                term { "tag" to "true" }
            }
            filter(false) {
                term { "tag" to "false" }
            }
            must_not(false) {
                range {
                    "age" to {
                        from = 10
                        to = 20
                    }
                }
            }
            should = listOf(
                    term { "tag" to "wow" },
                    term(false) { "tag" to "elasticsearch" })
            minimum_should_match = 1
            boost = 1.0f
        }

        query should_render_as """
        {
            "bool": {
                "must": [{
                    "term": {
                        "user": {
                            "value": "kimchy",
                            "boost": 1.0
                        }
                    }
                }],
                "filter": [{
                    "term": {
                        "tag": {
                            "value": "true",
                            "boost": 1.0
                        }
                    }
                }],
                "should": [{
                    "term": {
                        "tag": {
                            "value": "wow",
                            "boost": 1.0
                        }
                    }
                }],
                "disable_coord": false,
                "adjust_pure_negative": true,
                "minimum_should_match": "1",
                "boost": 1.0
            }
        }
        """
    }


    @Test
    fun `test bool`() {
        val query = bool {
            must {
                term { "user" to "kimchy" }
            }
            filter {
                term { "tag" to "tech" }
            }
            must_not {
                range {
                    "age" to {
                        from = 10
                        to = 20
                    }
                }
            }
            should = listOf(
                term { "tag" to "wow" },
                term { "tag" to "elasticsearch" })
            minimum_should_match = 1
            boost = 1.0f
        }

        query should_render_as """
        {
            "bool": {
                "must": [{
                    "term": {
                        "user": {
                            "value": "kimchy",
                            "boost": 1.0
                        }
                    }
                }],
                "filter": [{
                    "term": {
                        "tag": {
                            "value": "tech",
                            "boost": 1.0
                        }
                    }
                }],
                "must_not": [{
                    "range": {
                        "age": {
                            "from": 10,
                            "to": 20,
                            "include_lower": true,
                            "include_upper": true,
                            "boost": 1.0
                        }
                    }
                }],
                "should": [{
                    "term": {
                        "tag": {
                            "value": "wow",
                            "boost": 1.0
                        }
                    }
                }, {
                    "term": {
                        "tag": {
                            "value": "elasticsearch",
                            "boost": 1.0
                        }
                    }
                }],
                "disable_coord": false,
                "adjust_pure_negative": true,
                "minimum_should_match": "1",
                "boost": 1.0
            }
        }
        """
    }

}