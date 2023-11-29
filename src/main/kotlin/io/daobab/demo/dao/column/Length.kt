package io.daobab.demo.dao.column

import io.daobab.creation.DaobabCache
import io.daobab.model.*;

interface Length<E : Entity, F> : RelatedTo<E>, MapHandler<E> {

	fun getLength(): F = readParam("Length")

	fun setLength(value: F): E {
		return storeParam("Length", value)
	}

    /**
     * table:FILM, type:SMALLINT, size:16, nullable:true
     */
	fun colLength(): Column<E, F, out RelatedTo<E>> =
		DaobabCache.getColumn("Length", "LENGTH", this as Table<*>, Int::class.java) as  Column<E, F, out RelatedTo<E>> 
	
}
