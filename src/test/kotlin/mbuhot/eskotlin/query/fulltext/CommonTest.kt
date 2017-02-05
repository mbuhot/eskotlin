/*
 * Copyright (c) 2016. Michael Buhot m.buhot@gmail.com
 */

package mbuhot.eskotlin.query.fulltext

import mbuhot.eskotlin.query.should_be_null
import mbuhot.eskotlin.query.should_render_as
import org.elasticsearch.index.query.CommonTermsQueryBuilder
import org.junit.Test


class CommonTest {
    @Test
    fun `test common terms query`() {
        val query = common {
            "body" to {
                query = "this is bonsai cool"
                cutoff_frequency = 0.001f
            }
        }

        query should_render_as """
        {
            "common": {
                "body": {
                    "query": "this is bonsai cool",
                    "disable_coord": true,
                    "high_freq_operator": "OR",
                    "low_freq_operator": "OR",
                    "cutoff_frequency": 0.001,
                    "boost": 1.0
                }
            }
        }
        """
    }

    @Test
    fun `test common terms query disabled`() {
        val query = common(false) {
            "body" to {
                query = "this is bonsai cool"
                cutoff_frequency = 0.001f
            }
        }

        query.should_be_null()
    }

    @Test
    fun `test common terms with low_frequency operator`() {
        val query = common {
            "body" to {
                query = "nelly the elephant as a cartoon"
                cutoff_frequency = 0.001f
                low_freq_operator = "and"
            }
        }

        query should_render_as """
            {
                "common": {
                    "body": {
                        "query": "nelly the elephant as a cartoon",
                        "disable_coord": true,
                        "high_freq_operator": "OR",
                        "low_freq_operator": "AND",
                        "cutoff_frequency": 0.001,
                        "boost": 1.0
                    }
                }
            }
            """
    }

    @Test
    fun `test common terms with minimum_should_match`() {
        val query = common {
            "body" to {
                query = "nelly the elephant as a cartoon"
                cutoff_frequency = 0.001f
                minimum_should_match.low_freq = 2
            }
        }
        query should_render_as """
            {
                "common": {
                    "body": {
                        "query": "nelly the elephant as a cartoon",
                        "disable_coord": true,
                        "high_freq_operator": "OR",
                        "low_freq_operator": "OR",
                        "cutoff_frequency": 0.001,
                        "minimum_should_match": {
                            "low_freq": "2"
                        },
                        "boost": 1.0
                    }
                }
            }
        """
    }

    @Test
    fun `test common terms with separate minimum_should_match high and low`() {
        val query = common {
            "body" to {
                query = "nelly the elephant not as a cartoon"
                cutoff_frequency = 0.001f
                minimum_should_match {
                    low_freq = 2
                    high_freq = 3
                }
            }
        }

        query should_render_as """
            {
                "common": {
                    "body": {
                        "query": "nelly the elephant not as a cartoon",
                        "disable_coord": true,
                        "high_freq_operator": "OR",
                        "low_freq_operator": "OR",
                        "cutoff_frequency": 0.001,
                        "minimum_should_match": {
                            "low_freq": "2",
                            "high_freq": "3"
                        },
                        "boost": 1.0
                    }
                }
            }
            """
    }

    @Test
    fun `test common terms disabled parameters`() {
        val query = common {
            "body" to {
                query = "nelly the elephant not as a cartoon"
                cutoff_frequency = 0.001f
                minimum_should_match(true) {
                    low_freq = 2
                    high_freq = 3
                }
                minimum_should_match(false) {
                    low_freq = 4
                    high_freq = 5
                }
            }
        }

        query should_render_as """
            {
                "common": {
                    "body": {
                        "query": "nelly the elephant not as a cartoon",
                        "disable_coord": true,
                        "high_freq_operator": "OR",
                        "low_freq_operator": "OR",
                        "cutoff_frequency": 0.001,
                        "minimum_should_match": {
                            "low_freq": "2",
                            "high_freq": "3"
                        },
                        "boost": 1.0
                    }
                }
            }
            """
    }
}