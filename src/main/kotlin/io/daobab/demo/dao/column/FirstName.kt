package io.daobab.demo.dao.column

import io.daobab.creation.DaobabCache
import io.daobab.model.*;

interface FirstName<E : Entity, F> : RelatedTo<E>, MapHandler<E> {

	fun getFirstName(): F = readParam("FirstName")

	fun setFirstName(value: F): E {
		return storeParam("FirstName", value)
	}

    /**
     * table:ACTOR, type:VARCHAR, size:45, nullable:false
     * table:CUSTOMER, type:VARCHAR, size:45, nullable:false
     * table:STAFF, type:VARCHAR, size:45, nullable:false
     */
	fun colFirstName(): Column<E, F, out RelatedTo<E>> =
		DaobabCache.getColumn("FirstName", "FIRST_NAME", this as Table<*>, String::class.java) as  Column<E, F, out RelatedTo<E>> 
	
}
