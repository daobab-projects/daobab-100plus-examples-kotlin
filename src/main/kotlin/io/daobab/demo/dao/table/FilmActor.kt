package io.daobab.demo.dao.table

import io.daobab.demo.dao.column.ActorId
import io.daobab.demo.dao.column.FilmId
import io.daobab.demo.dao.column.LastUpdate

import io.daobab.creation.DaobabCache
import io.daobab.model.*
import java.time.LocalDateTime
import java.util.*

class FilmActor : Table<FilmActor>, 
	FilmActorKey<FilmActor>,
	ActorId<FilmActor, Int>,
	FilmId<FilmActor, Int>,
	LastUpdate<FilmActor, LocalDateTime>,

	PrimaryCompositeKey<FilmActor,FilmActorKey<FilmActor>>
	{

	constructor (): super()

	constructor (parameters: Map<String?, Any?>?): super(parameters)

	override fun columns(): List<TableColumn> = 
		DaobabCache.getTableColumns(this) {
			Arrays.asList(
			TableColumn(colActorId()).primaryKey().size(16),
			TableColumn(colFilmId()).primaryKey().size(16),
			TableColumn(colLastUpdate()).size(26).scale(6)

	)
}
	
	override fun colCompositeId(): CompositeColumns<FilmActorKey<FilmActor>> {
		return  compositeFilmActorKey()
		}
}
