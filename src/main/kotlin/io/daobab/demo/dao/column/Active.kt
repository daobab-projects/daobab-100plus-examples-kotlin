package io.daobab.demo.dao.column

import io.daobab.creation.DaobabCache
import io.daobab.model.*;

interface Active<E : Entity, F> : RelatedTo<E>, MapHandler<E> {

	fun getActive(): F = readParam("Active")

	fun setActive(value: F): E {
		return storeParam("Active", value)
	}

    /**
     * table:CUSTOMER, type:BOOLEAN, size:1, nullable:false
     * table:STAFF, type:BOOLEAN, size:1, nullable:false
     */
	fun colActive(): Column<E, F, out RelatedTo<E>> =
		DaobabCache.getColumn("Active", "ACTIVE", this as Table<*>, Boolean::class.java) as  Column<E, F, out RelatedTo<E>> 
	
}
