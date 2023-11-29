package io.daobab.demo.dao.column

import io.daobab.creation.DaobabCache
import io.daobab.model.*;

interface Phone<E : Entity, F> : RelatedTo<E>, MapHandler<E> {

	fun getPhone(): F = readParam("Phone")

	fun setPhone(value: F): E {
		return storeParam("Phone", value)
	}

    /**
     * table:ADDRESS, type:VARCHAR, size:20, nullable:false
     */
	fun colPhone(): Column<E, F, out RelatedTo<E>> =
		DaobabCache.getColumn("Phone", "PHONE", this as Table<*>, String::class.java) as  Column<E, F, out RelatedTo<E>> 
	
}
