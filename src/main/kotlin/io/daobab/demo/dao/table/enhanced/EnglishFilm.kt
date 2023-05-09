package io.daobab.demo.dao.table.enhanced

import io.daobab.demo.dao.Lang
import io.daobab.demo.dao.table.Film
import io.daobab.demo.dao.table.Language
import io.daobab.model.EnhancedEntity
import io.daobab.query.base.Query
import io.daobab.query.base.QueryJoin

class EnglishFilm : Film(), EnhancedEntity {
    override fun <Q> enhanceQuery(query: Q): Q where Q : Query<*, *, *>, Q : QueryJoin<Q> {
        return query.join(tabLanguage, colLanguageId(), and().equal(tabLanguage.colName(), Lang.English))
    }

    companion object {
        private val tabLanguage = Language()
    }
}
