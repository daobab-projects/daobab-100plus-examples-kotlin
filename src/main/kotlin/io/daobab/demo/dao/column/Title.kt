package io.daobab.demo.dao.column

import io.daobab.creation.DaobabCache
import io.daobab.model.*;

interface Title<E : Entity, F> : RelatedTo<E>, MapHandler<E> {

	fun getTitle(): F = readParam("Title")

	fun setTitle(value: F): E {
		return storeParam("Title", value)
	}

    /**
     * table:FILM, type:VARCHAR, size:255, nullable:false
     * table:FILM_TEXT, type:VARCHAR, size:255, nullable:false
     */
	fun colTitle(): Column<E, F, out RelatedTo<E>> =
		DaobabCache.getColumn("Title", "TITLE", this as Table<*>, String::class.java) as  Column<E, F, out RelatedTo<E>> 
	
}
