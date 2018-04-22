/*
 * Copyright (c) 2016. Michael Buhot m.buhot@gmail.com
 */

package mbuhot.eskotlin.query.compound

import mbuhot.eskotlin.query.should_render_as
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
                    "age" {
                        from = 10
                        to = 20
                    }
                }
            }
            should {
                term { "tag" to "wow" }
                term { "tag" to "elasticsearch" }
            }
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

    @Test
    fun `test bool multi query syntax`() {
        val query = bool {
            must {
                term { "user" to "kimchy" }
                term { "user" to "kimchy" }
            }
            filter {
                term { "tag" to "tech" }
                term { "tag" to "tech" }
            }
            must_not {
                term { "tag" to "tech" }
                range {
                    "age" {
                        from = 10
                        to = 20
                    }
                }
            }
            should {
                term { "tag" to "wow" }
                term { "tag" to "elasticsearch" }
            }
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
                }, {
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
                }, {
                    "term": {
                        "tag": {
                            "value": "tech",
                            "boost": 1.0
                        }
                    }
                }],
                "must_not": [{
                    "term": {
                        "tag": {
                            "value": "tech",
                            "boost": 1.0
                        }
                    }
                }, {
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