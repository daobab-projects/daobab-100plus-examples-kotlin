package io.daobab.demo.dao.table

import io.daobab.demo.dao.column.CategoryId
import io.daobab.demo.dao.column.Name
import io.daobab.demo.dao.column.LastUpdate

import io.daobab.creation.DaobabCache
import io.daobab.model.*
import java.time.LocalDateTime
import java.util.*

class Category : Table<Category>, 
	CategoryId<Category, Int>,
	Name<Category, String>,
	LastUpdate<Category, LocalDateTime>,

	PrimaryKey<Category,Int,CategoryId<*, Int>>
	{

	constructor (): super()

	constructor (parameters: Map<String?, Any?>?): super(parameters)

	override fun columns(): List<TableColumn> = 
		DaobabCache.getTableColumns(this) {
			Arrays.asList(
			TableColumn(colCategoryId()).primaryKey().size(8),
			TableColumn(colName()).size(25),
			TableColumn(colLastUpdate()).size(26).scale(6)

	)
}
	
override fun colID(): Column<Category, Int, CategoryId<*, Int>> = colCategoryId() as Column< Category, Int, CategoryId<*, Int>>

	override fun hashCode() = Objects.hashCode(id)

	override fun equals(obj: Any?): Boolean {
		if (this === obj) return true
		if (obj == null) return false
		if (javaClass != obj.javaClass) return false
		val other: PrimaryKey<*, *, *> = obj as PrimaryKey<*, *, *>
		return id == other.id
	}

}
