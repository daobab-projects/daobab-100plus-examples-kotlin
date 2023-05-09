package io.daobab.demo.dao.table

import io.daobab.demo.dao.column.FilmId
import io.daobab.demo.dao.column.CategoryId
import io.daobab.demo.dao.column.LastUpdate

import io.daobab.model.CompositeColumns
import io.daobab.model.Composite
import io.daobab.model.EntityMap
import io.daobab.model.TableColumn

interface FilmCategoryKey<E : EntityMap>
	:  FilmId<E, Int>, CategoryId<E, Int>, Composite<E>{

	fun compositeFilmCategoryKey() = 
		CompositeColumns<FilmCategoryKey<E>>(
			TableColumn(colFilmId()).primaryKey().size(16),
			TableColumn(colCategoryId()).primaryKey().size(8))


}
