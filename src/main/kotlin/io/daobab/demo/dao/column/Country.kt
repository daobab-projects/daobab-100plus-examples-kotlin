package io.daobab.demo.dao.column

import io.daobab.creation.DaobabCache
import io.daobab.model.*;

interface Country<E : Entity, F> : RelatedTo<E>, MapHandler<E> {

	fun getCountry(): F = readParam("Country")

	fun setCountry(value: F): E {
		return storeParam("Country", value)
	}

    /**
     * table:COUNTRY, type:VARCHAR, size:50, nullable:false
     */
	fun colCountry(): Column<E, F, out RelatedTo<E>> =
		DaobabCache.getColumn("Country", "COUNTRY", this as Table<*>, String::class.java) as  Column<E, F, out RelatedTo<E>> 
	
}
