package io.daobab.demo.dao.column

import io.daobab.creation.DaobabCache
import io.daobab.model.*;

interface FilmId<E : Entity, F> : RelatedTo<E>, MapHandler<E> {

	fun getFilmId(): F = readParam("FilmId")

	fun setFilmId(value: F): E {
		return storeParam("FilmId", value)
	}

    /**
     * table:FILM, type:SMALLINT, size:16, nullable:false
     * table:FILM_ACTOR, type:SMALLINT, size:16, nullable:false
     * table:FILM_CATEGORY, type:SMALLINT, size:16, nullable:false
     * table:FILM_TEXT, type:SMALLINT, size:16, nullable:false
     * table:INVENTORY, type:SMALLINT, size:16, nullable:false
     */
	fun colFilmId(): Column<E, F, out RelatedTo<E>> =
		DaobabCache.getColumn("FilmId", "FILM_ID", this as Table<*>, Int::class.java) as  Column<E, F, out RelatedTo<E>> 
	
}
