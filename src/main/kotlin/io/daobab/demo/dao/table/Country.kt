package io.daobab.demo.dao.table

import io.daobab.demo.dao.column.CountryId
import io.daobab.demo.dao.column.LastUpdate

import io.daobab.creation.DaobabCache
import io.daobab.model.*
import java.time.LocalDateTime
import java.util.*

class Country : Table<Country>, 
	CountryId<Country, Int>,
	io.daobab.demo.dao.column.Country<Country, String>,
	LastUpdate<Country, LocalDateTime>,

	PrimaryKey<Country,Int,CountryId<*, Int>>
	{

	constructor (): super()

	constructor (parameters: Map<String?, Any?>?): super(parameters)

	override fun columns(): List<TableColumn> = 
		DaobabCache.getTableColumns(this) {
			Arrays.asList(
			TableColumn(colCountryId()).primaryKey().size(16),
			TableColumn(colCountry()).size(50),
			TableColumn(colLastUpdate()).size(26).scale(6)

	)
}
	
override fun colID(): Column<Country, Int, CountryId<*, Int>> = colCountryId() as Column< Country, Int, CountryId<*, Int>>

	override fun hashCode() = Objects.hashCode(id)

	override fun equals(obj: Any?): Boolean {
		if (this === obj) return true
		if (obj == null) return false
		if (javaClass != obj.javaClass) return false
		val other: PrimaryKey<*, *, *> = obj as PrimaryKey<*, *, *>
		return id == other.id
	}

}
