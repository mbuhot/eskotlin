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
class BoostingTest {

    @Test
    fun `test boosting`() {
        val query = boosting {
            positive {
                term {
                    "field1" to "value1"
                }
            }
            negative {
                term {
                    "field2" to "value2"
                }
            }
            negative_boost = 0.2f
        }

        query should_render_as """
            {
                "boosting": {
                    "positive": {
                        "term": {
                            "field1": {
                                "value": "value1",
                                "boost": 1.0
                            }
                        }
                    },
                    "negative": {
                        "term": {
                            "field2": {
                                "value": "value2",
                                "boost": 1.0
                            }
                        }
                    },
                    "negative_boost": 0.2,
                    "boost": 1.0
                }
            }
            """
    }

    @Test
    fun `test boosting disabled`() {
        val query = runIf(false) {
            boosting {
                positive {
                    term {
                        "field1" to "value1"
                    }
                }
                negative {
                    term {
                        "field2" to "value2"
                    }
                }
                negative_boost = 0.2f
            }
        }
        query.should_be_null()
    }

    @Test
    fun `test boosting disabled parameters`() {
        val query = boosting {
            positive {
                term {
                    "field1" to "value1"
                }
            }
            runIf(false) {
                negative {
                    term {
                        "field2" to "value2"
                    }
                }
            }
            runIf(true) {
                negative {
                    term {
                        "field3" to "value3"
                    }
                }
            }
            negative_boost = 0.2f
        }

        query should_render_as """
            {
                "boosting": {
                    "positive": {
                        "term": {
                            "field1": {
                                "value": "value1",
                                "boost": 1.0
                            }
                        }
                    },
                    "negative": {
                        "term": {
                            "field3": {
                                "value": "value3",
                                "boost": 1.0
                            }
                        }
                    },
                    "negative_boost": 0.2,
                    "boost": 1.0
                }
            }
            """
    }
}