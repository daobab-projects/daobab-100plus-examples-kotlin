package io.daobab.demo.dao.table

import io.daobab.demo.dao.column.LanguageId
import io.daobab.demo.dao.column.Name
import io.daobab.demo.dao.column.LastUpdate

import io.daobab.creation.DaobabCache
import io.daobab.demo.dao.Lang
import io.daobab.demo.dao.column.NameLang
import io.daobab.model.*
import java.time.LocalDateTime
import java.util.*

class Language : Table<Language>, 
	LanguageId<Language, Int>,
	NameLang<Language, Lang>,
	LastUpdate<Language, LocalDateTime>,

	PrimaryKey<Language,Int,LanguageId<*, Int>>
	{

	constructor (): super()

	constructor (parameters: Map<String?, Any?>?): super(parameters)

	override fun columns(): List<TableColumn> = 
		DaobabCache.getTableColumns(this) {
			Arrays.asList(
			TableColumn(colLanguageId()).primaryKey().size(8),
			TableColumn(colName()).size(20),
			TableColumn(colLastUpdate()).size(26).scale(6)

	)
}
	
override fun colID(): Column<Language, Int, LanguageId<*, Int>> = colLanguageId() as Column< Language, Int, LanguageId<*, Int>>

	override fun hashCode() = Objects.hashCode(id)

	override fun equals(obj: Any?): Boolean {
		if (this === obj) return true
		if (obj == null) return false
		if (javaClass != obj.javaClass) return false
		val other: PrimaryKey<*, *, *> = obj as PrimaryKey<*, *, *>
		return id == other.id
	}

}
