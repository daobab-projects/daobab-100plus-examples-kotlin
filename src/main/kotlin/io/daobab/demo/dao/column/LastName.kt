package io.daobab.demo.dao.column

import io.daobab.creation.DaobabCache
import io.daobab.model.*;

interface LastName<E : Entity, F> : RelatedTo<E>, MapHandler<E> {

	fun getLastName(): F = readParam("LastName")

	fun setLastName(value: F): E {
		return storeParam("LastName", value)
	}

    /**
     * table:ACTOR, type:VARCHAR, size:45, nullable:false
     * table:CUSTOMER, type:VARCHAR, size:45, nullable:false
     * table:STAFF, type:VARCHAR, size:45, nullable:false
     */
	fun colLastName(): Column<E, F, out RelatedTo<E>> =
		DaobabCache.getColumn("LastName", "LAST_NAME", this as Table<*>, String::class.java) as  Column<E, F, out RelatedTo<E>> 
	
}
