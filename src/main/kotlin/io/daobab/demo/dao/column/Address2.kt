package io.daobab.demo.dao.column

import io.daobab.creation.DaobabCache
import io.daobab.model.*;

interface Address2<E : Entity, F> : RelatedTo<E>, MapHandler<E> {

	fun getAddress2(): F = readParam("Address2")

	fun setAddress2(value: F): E {
		return storeParam("Address2", value)
	}

    /**
     * table:ADDRESS, type:VARCHAR, size:50, nullable:true
     */
	fun colAddress2(): Column<E, F, out RelatedTo<E>> =
		DaobabCache.getColumn("Address2", "ADDRESS2", this as Table<*>, String::class.java) as  Column<E, F, out RelatedTo<E>> 
	
}
