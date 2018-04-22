package mbuhot.eskotlin.query

import mbuhot.eskotlin.query.compound.*
import mbuhot.eskotlin.query.fulltext.*
import mbuhot.eskotlin.query.joining.HasChildData
import mbuhot.eskotlin.query.joining.HasParentData
import mbuhot.eskotlin.query.joining.NestedData
import mbuhot.eskotlin.query.term.*
import org.elasticsearch.index.query.MatchAllQueryBuilder
import org.elasticsearch.index.query.QueryBuilder

/**
 * Created by vince(me@liuwj.me) on Apr 21, 2018.
 */
class ListQueriesBlock {
    val queries = ArrayList<QueryBuilder>()

    fun bool(init: BoolData.() -> Unit) {
        queries += mbuhot.eskotlin.query.compound.bool(init)
    }

    fun boosting(init: BoostingData.() -> Unit) {
        queries += mbuhot.eskotlin.query.compound.boosting(init)
    }

    fun constant_score(init: ConstantScoreData.() -> Unit) {
        queries += mbuhot.eskotlin.query.compound.constant_score(init)
    }

    fun dis_max(init: DisMaxData.() -> Unit) {
        queries += mbuhot.eskotlin.query.compound.dis_max(init)
    }

    fun function_score(init: FunctionScoreData.() -> Unit) {
        queries += mbuhot.eskotlin.query.compound.function_score(init)
    }

    fun common(init: CommonBlock.() -> CommonBlock.CommonData) {
        queries += mbuhot.eskotlin.query.fulltext.common(init)
    }

    fun match(init: MatchBlock.() -> MatchBlock.MatchData) {
        queries += mbuhot.eskotlin.query.fulltext.match(init)
    }

    fun match_phrase(init: MatchPhraseBlock.() -> MatchPhraseBlock.MatchPhraseData) {
        queries += mbuhot.eskotlin.query.fulltext.match_phrase(init)
    }

    fun match_phrase_prefix(init: MatchPhrasePrefixBlock.() -> MatchPhrasePrefixBlock.MatchPhrasePrefixData) {
        queries += mbuhot.eskotlin.query.fulltext.match_phrase_prefix(init)
    }

    fun multi_match(init: MultiMatchData.() -> Unit) {
        queries += mbuhot.eskotlin.query.fulltext.multi_match(init)
    }

    fun has_child(init: HasChildData.() -> Unit) {
        queries += mbuhot.eskotlin.query.joining.has_child(init)
    }

    fun has_parent(init: HasParentData.() -> Unit) {
        queries += mbuhot.eskotlin.query.joining.has_parent(init)
    }

    fun nested(init: NestedData.() -> Unit) {
        queries += mbuhot.eskotlin.query.joining.nested(init)
    }

    fun exists(init: ExistsData.() -> Unit) {
        queries += mbuhot.eskotlin.query.term.exists(init)
    }

    fun fuzzy(init: FuzzyBlock.() -> FuzzyBlock.FuzzyData) {
        queries += mbuhot.eskotlin.query.term.fuzzy(init)
    }

    fun ids(init: IdsData.() -> Unit) {
        queries += mbuhot.eskotlin.query.term.ids(init)
    }

    fun match_all(init: MatchAllQueryBuilder.() -> Unit) {
        queries += mbuhot.eskotlin.query.term.match_all(init)
    }

    fun prefix(init: PrefixBlock.() -> PrefixBlock.PrefixData) {
        queries += mbuhot.eskotlin.query.term.prefix(init)
    }

    fun range(init: RangeBlock.() -> RangeBlock.RangeData) {
        queries += mbuhot.eskotlin.query.term.range(init)
    }

    fun regexp(init: RegexpBlock.() -> RegexpBlock.RegexpData) {
        queries += mbuhot.eskotlin.query.term.regexp(init)
    }

    fun term(init: TermBlock.() -> TermBlock.TermData) {
        queries += mbuhot.eskotlin.query.term.term(init)
    }

    fun terms(init: TermsBlock.() -> TermsBlock.TermsData) {
        queries += mbuhot.eskotlin.query.term.terms(init)
    }

    fun type(init: TypeData.() -> Unit) {
        queries += mbuhot.eskotlin.query.term.type(init)
    }

    fun wildcard(init: WildcardBlock.() -> WildcardBlock.WildcardData) {
        queries += mbuhot.eskotlin.query.term.wildcard(init)
    }
}