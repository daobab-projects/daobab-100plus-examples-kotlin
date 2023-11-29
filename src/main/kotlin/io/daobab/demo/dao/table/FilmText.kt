package io.daobab.demo.dao.table

import io.daobab.demo.dao.column.FilmId
import io.daobab.demo.dao.column.Title
import io.daobab.demo.dao.column.Description

import io.daobab.creation.DaobabCache
import io.daobab.model.*

import java.util.*

class FilmText : Table<FilmText>, 
	FilmId<FilmText, Int>,
	Title<FilmText, String>,
	Description<FilmText, String?>,

	PrimaryKey<FilmText,Int,FilmId<*, Int>>
	{

	constructor (): super()

	constructor (parameters: Map<String?, Any?>?): super(parameters)

	override fun columns(): List<TableColumn> = 
		DaobabCache.getTableColumns(this) {
			Arrays.asList(
			TableColumn(colFilmId()).primaryKey().size(16),
			TableColumn(colTitle()).size(255),
			TableColumn(colDescription()).size(1000000000)

	)
}
	
override fun colID(): Column<FilmText, Int, FilmId<*, Int>> = colFilmId() as Column< FilmText, Int, FilmId<*, Int>>

	override fun hashCode() = Objects.hashCode(id)

	override fun equals(obj: Any?): Boolean {
		if (this === obj) return true
		if (obj == null) return false
		if (javaClass != obj.javaClass) return false
		val other: PrimaryKey<*, *, *> = obj as PrimaryKey<*, *, *>
		return id == other.id
	}

}
