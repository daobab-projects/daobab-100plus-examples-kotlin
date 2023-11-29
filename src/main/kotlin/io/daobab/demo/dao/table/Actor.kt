package io.daobab.demo.dao.table

import io.daobab.demo.dao.column.ActorId
import io.daobab.demo.dao.column.FirstName
import io.daobab.demo.dao.column.LastName
import io.daobab.demo.dao.column.LastUpdate

import io.daobab.creation.DaobabCache
import io.daobab.model.*
import java.time.LocalDateTime
import java.util.*

class Actor : Table<Actor>, 
	ActorId<Actor, Int>,
	FirstName<Actor, String>,
	LastName<Actor, String>,
	LastUpdate<Actor, LocalDateTime>,

	PrimaryKey<Actor,Int,ActorId<*, Int>>
	{

	constructor (): super()

	constructor (parameters: Map<String?, Any?>?): super(parameters)

	override fun columns(): List<TableColumn> = 
		DaobabCache.getTableColumns(this) {
			Arrays.asList(
			TableColumn(colActorId()).primaryKey().size(16),
			TableColumn(colFirstName()).size(45),
			TableColumn(colLastName()).size(45),
			TableColumn(colLastUpdate()).size(26).scale(6)

	)
}
	
override fun colID(): Column<Actor, Int, ActorId<*, Int>> = colActorId() as Column< Actor, Int, ActorId<*, Int>>

	override fun hashCode() = Objects.hashCode(id)

	override fun equals(obj: Any?): Boolean {
		if (this === obj) return true
		if (obj == null) return false
		if (javaClass != obj.javaClass) return false
		val other: PrimaryKey<*, *, *> = obj as PrimaryKey<*, *, *>
		return id == other.id
	}

}
