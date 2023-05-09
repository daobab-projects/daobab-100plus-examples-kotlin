package io.daobab.demo.dao.table

import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.JsonInclude
import io.daobab.clone.EntityDuplicator
import io.daobab.model.Column
import io.daobab.model.TableColumn
import io.daobab.model.PrimaryCompositeKey
import io.daobab.model.CompositeColumns
import io.daobab.demo.dao.column.ActorId
import io.daobab.demo.dao.column.FilmId
import io.daobab.demo.dao.column.LastUpdate

import io.daobab.model.Table

import java.time.LocalDateTime
import java.util.*

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonAutoDetect(fieldVisibility = ANY, getterVisibility = NONE, setterVisibility = NONE)
open class FilmActor : Table(),
	FilmActorKey<FilmActor>,
	ActorId<FilmActor, Int>,
	FilmId<FilmActor, Int>,
	LastUpdate<FilmActor, LocalDateTime>,

	PrimaryCompositeKey<FilmActor,FilmActorKey<FilmActor>>
	{

	override fun getEntityName() = "FILM_ACTOR"

	override fun columns() = 
		listOf(
			TableColumn(colActorId()).primaryKey().size(16),
			TableColumn(colFilmId()).primaryKey().size(16),
			TableColumn(colLastUpdate()).size(26).scale(6)
		)

	override fun clone(): FilmActor  {
		return EntityDuplicator.cloneEntity(this)
	}

		override fun colCompositeId(): CompositeColumns<FilmActorKey<FilmActor>> {
		return  compositeFilmActorKey()
		}


}
