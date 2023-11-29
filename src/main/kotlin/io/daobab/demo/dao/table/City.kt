package io.daobab.demo.dao.table

import io.daobab.demo.dao.column.CityId
import io.daobab.demo.dao.column.CountryId
import io.daobab.demo.dao.column.LastUpdate

import io.daobab.creation.DaobabCache
import io.daobab.model.*
import java.time.LocalDateTime
import java.util.*

open class City : Table<City>,
	CityId<City, Int>,
	io.daobab.demo.dao.column.City<City, String>,
	CountryId<City, Int>,
	LastUpdate<City, LocalDateTime>,

	PrimaryKey<City,Int,CityId<*, Int>>
	{

	constructor (): super()

	constructor (parameters: Map<String?, Any?>?): super(parameters)

	override fun columns(): List<TableColumn> = 
		DaobabCache.getTableColumns(this) {
			Arrays.asList(
			TableColumn(colCityId()).primaryKey().size(16),
			TableColumn(colCity()).size(50),
			TableColumn(colCountryId()).size(16),
			TableColumn(colLastUpdate()).size(26).scale(6)

	)
}
	
override fun colID(): Column<City, Int, CityId<*, Int>> = colCityId() as Column< City, Int, CityId<*, Int>>

	override fun hashCode() = Objects.hashCode(id)

	override fun equals(obj: Any?): Boolean {
		if (this === obj) return true
		if (obj == null) return false
		if (javaClass != obj.javaClass) return false
		val other: PrimaryKey<*, *, *> = obj as PrimaryKey<*, *, *>
		return id == other.id
	}

}
