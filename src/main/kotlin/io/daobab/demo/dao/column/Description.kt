package io.daobab.demo.dao.column

import io.daobab.creation.DaobabCache
import io.daobab.model.*;

interface Description<E : Entity, F> : RelatedTo<E>, MapHandler<E> {

	fun getDescription(): F = readParam("Description")

	fun setDescription(value: F): E {
		return storeParam("Description", value)
	}

    /**
     * table:FILM, type:VARCHAR, size:1000000000, nullable:true
     * table:FILM_TEXT, type:VARCHAR, size:1000000000, nullable:true
     */
	fun colDescription(): Column<E, F, out RelatedTo<E>> =
		DaobabCache.getColumn("Description", "DESCRIPTION", this as Table<*>, String::class.java) as  Column<E, F, out RelatedTo<E>> 
	
}
