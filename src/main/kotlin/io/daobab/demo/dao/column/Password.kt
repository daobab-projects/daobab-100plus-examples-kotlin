package io.daobab.demo.dao.column

import io.daobab.creation.DaobabCache
import io.daobab.model.*;

interface Password<E : Entity, F> : RelatedTo<E>, MapHandler<E> {

	fun getPassword(): F = readParam("Password")

	fun setPassword(value: F): E {
		return storeParam("Password", value)
	}

    /**
     * table:STAFF, type:VARCHAR, size:40, nullable:true
     */
	fun colPassword(): Column<E, F, out RelatedTo<E>> =
		DaobabCache.getColumn("Password", "PASSWORD", this as Table<*>, String::class.java) as  Column<E, F, out RelatedTo<E>> 
	
}
