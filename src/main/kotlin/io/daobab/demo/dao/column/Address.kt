package io.daobab.demo.dao.column

import io.daobab.creation.DaobabCache
import io.daobab.model.*;

interface Address<E : Entity, F> : RelatedTo<E>, MapHandler<E> {

	fun getAddress(): F = readParam("Address")

	fun setAddress(value: F): E {
		return storeParam("Address", value)
	}

    /**
     * table:ADDRESS, type:VARCHAR, size:50, nullable:false
     */
	fun colAddress(): Column<E, F, out RelatedTo<E>> =
		DaobabCache.getColumn("Address", "ADDRESS", this as Table<*>, String::class.java) as  Column<E, F, out RelatedTo<E>> 
	
}
