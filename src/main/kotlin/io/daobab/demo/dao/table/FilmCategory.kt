package io.daobab.demo.dao.table

import io.daobab.demo.dao.column.FilmId
import io.daobab.demo.dao.column.CategoryId
import io.daobab.demo.dao.column.LastUpdate

import io.daobab.creation.DaobabCache
import io.daobab.model.*
import java.time.LocalDateTime
import java.util.*

class FilmCategory : Table<FilmCategory>, 
	FilmCategoryKey<FilmCategory>,
	FilmId<FilmCategory, Int>,
	CategoryId<FilmCategory, Int>,
	LastUpdate<FilmCategory, LocalDateTime>,

	PrimaryCompositeKey<FilmCategory,FilmCategoryKey<FilmCategory>>
	{

	constructor (): super()

	constructor (parameters: Map<String?, Any?>?): super(parameters)

	override fun columns(): List<TableColumn> = 
		DaobabCache.getTableColumns(this) {
			Arrays.asList(
			TableColumn(colFilmId()).primaryKey().size(16),
			TableColumn(colCategoryId()).primaryKey().size(8),
			TableColumn(colLastUpdate()).size(26).scale(6)

	)
}
	
	override fun colCompositeId(): CompositeColumns<FilmCategoryKey<FilmCategory>> {
		return  compositeFilmCategoryKey()
		}
}
