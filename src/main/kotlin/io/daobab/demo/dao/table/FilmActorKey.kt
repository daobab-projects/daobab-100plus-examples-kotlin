package io.daobab.demo.dao.table

import io.daobab.demo.dao.column.ActorId
import io.daobab.demo.dao.column.FilmId
import io.daobab.demo.dao.column.LastUpdate

import io.daobab.model.CompositeColumns
import io.daobab.model.Composite
import io.daobab.model.Entity
import io.daobab.model.TableColumn

interface FilmActorKey<E : Entity>
	:  ActorId<E, Int>, FilmId<E, Int>, Composite<E>{

	
	fun compositeFilmActorKey() = 
		CompositeColumns<FilmActorKey<E>>(
			TableColumn(colActorId()).primaryKey().size(16),
			TableColumn(colFilmId()).primaryKey().size(16))


}
