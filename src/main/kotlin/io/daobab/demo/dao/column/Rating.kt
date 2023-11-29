package io.daobab.demo.dao.column

import io.daobab.creation.DaobabCache
import io.daobab.model.*;

interface Rating<E : Entity, F> : RelatedTo<E>, MapHandler<E> {

	fun getRating(): F = readParam("Rating")

	fun setRating(value: F): E {
		return storeParam("Rating", value)
	}

    /**
     * table:FILM, type:VARCHAR, size:5, nullable:true
     */
	fun colRating(): Column<E, F, out RelatedTo<E>> =
		DaobabCache.getColumn("Rating", "RATING", this as Table<*>, String::class.java) as  Column<E, F, out RelatedTo<E>> 
	
}
