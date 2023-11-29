package io.daobab.demo.dao.column

import io.daobab.creation.DaobabCache
import io.daobab.model.*;

interface Email<E : Entity, F> : RelatedTo<E>, MapHandler<E> {

	fun getEmail(): F = readParam("Email")

	fun setEmail(value: F): E {
		return storeParam("Email", value)
	}

    /**
     * table:CUSTOMER, type:VARCHAR, size:50, nullable:true
     * table:STAFF, type:VARCHAR, size:50, nullable:true
     */
	fun colEmail(): Column<E, F, out RelatedTo<E>> =
		DaobabCache.getColumn("Email", "EMAIL", this as Table<*>, String::class.java) as  Column<E, F, out RelatedTo<E>> 
	
}
