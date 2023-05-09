package io.daobab.demo.dao.table.enhanced

import io.daobab.demo.dao.column.Country
import io.daobab.demo.dao.table.City
import io.daobab.model.Column
import io.daobab.model.EnhancedEntity
import io.daobab.query.base.Query
import io.daobab.query.base.QueryJoin

class CountryCity : City(), Country<City,String>, EnhancedEntity {
    override fun joinedColumns(): List<Column<*, *, *>> {
        return listOf(
            tabCountry.colCountry()
        )
    }

    override fun <Q> enhanceQuery(query: Q): Q where Q : Query<*, *, *>, Q : QueryJoin<Q> {
        return query
            .join(tabCountry, colCountryId()) as Q
    }

    companion object {
        private val tabCountry = io.daobab.demo.dao.table.Country()
    }
}
