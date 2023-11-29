package io.daobab.demo.dao.column

import io.daobab.creation.DaobabCache
import io.daobab.model.*;

interface City<E : Entity, F> : RelatedTo<E>, MapHandler<E> {

	fun getCity(): F = readParam("City")

	fun setCity(value: F): E {
		return storeParam("City", value)
	}

    /**
     * table:CITY, type:VARCHAR, size:50, nullable:false
     */
	fun colCity(): Column<E, F, out RelatedTo<E>> =
		DaobabCache.getColumn("City", "CITY", this as Table<*>, String::class.java) as  Column<E, F, out RelatedTo<E>> 
	
}
