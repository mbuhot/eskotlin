# 0.7.1

 - Add support for named queries
 
# 0.7.0

 - Add support for filter and nested aggregations, thanks @nzcoffeem !
 - Update build script to Gradle 5

# 0.6.0

 - Update elasticsearch version to 6.3
 - `IndicesQueryBuilder` removed,
 - `disable_coord` property of `CommonTermsQueryBuilder` removed
 - Seed for `randomFunction` is no longer accepted as constructor parameter

# 0.5.0

 - New syntax available for string keyed objects, no longer requires using `to` when the right side is initialized with a lambda.
 - Update to kotlin 1.2.60

 Thanks to @vincentlauvlwj and @roschlau for contributions!

# 0.4.0

 - Add TermsLookup support for `terms` query
 - Update Gradle version to 4.5.1
 - Update Kotlin version to 1.2.30
 - Update elasticsearch version to 5.6.5
 - Import HasChild/HasParent from org.elasticsearch.plugin:parent-join-client module

# 0.3.0

 - Update Kotlin version to 1.1.3-2
 - Adding the ability to accept field boost for multi match

# 0.2.0

 - Added match_phrase & match_phrase_prefix
 - Updated to elasticsearch 5.0.0

# 0.1.0

 - Initial release
 - Targets Elastic Search version 2.2.x
