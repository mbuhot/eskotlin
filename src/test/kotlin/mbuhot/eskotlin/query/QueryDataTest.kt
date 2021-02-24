package mbuhot.eskotlin.query

import org.elasticsearch.index.query.BoolQueryBuilder
import org.junit.Test

/**
 * Created on 02/24/2021
 * @author Vishwanath Sundareshan (vishwahere@gmail.com)
 */
internal class QueryDataTest{
    @Test
    fun `boost and query name is applied`() {
        val queryData = QueryData()
        queryData.boost = 1f
        queryData.queryName = "myquery"

        val boolQuery = BoolQueryBuilder()
        boolQuery.initQuery(queryData)

        boolQuery.toString() should_render_as """
            {
              "bool" : {
                "adjust_pure_negative" : true,
                "boost" : 1.0,
                "_name" : "myquery"
              }
            }
        """
    }
}
