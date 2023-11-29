package io.daobab.demo.dao.column

import io.daobab.creation.DaobabCache
import io.daobab.model.*;

interface Username<E : Entity, F> : RelatedTo<E>, MapHandler<E> {

	fun getUsername(): F = readParam("Username")

	fun setUsername(value: F): E {
		return storeParam("Username", value)
	}

    /**
     * table:STAFF, type:VARCHAR, size:16, nullable:false
     */
	fun colUsername(): Column<E, F, out RelatedTo<E>> =
		DaobabCache.getColumn("Username", "USERNAME", this as Table<*>, String::class.java) as  Column<E, F, out RelatedTo<E>> 
	
}
