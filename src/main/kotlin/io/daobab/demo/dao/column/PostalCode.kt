package io.daobab.demo.dao.column

import io.daobab.creation.DaobabCache
import io.daobab.model.*;

interface PostalCode<E : Entity, F> : RelatedTo<E>, MapHandler<E> {

	fun getPostalCode(): F = readParam("PostalCode")

	fun setPostalCode(value: F): E {
		return storeParam("PostalCode", value)
	}

    /**
     * table:ADDRESS, type:VARCHAR, size:10, nullable:true
     */
	fun colPostalCode(): Column<E, F, out RelatedTo<E>> =
		DaobabCache.getColumn("PostalCode", "POSTAL_CODE", this as Table<*>, String::class.java) as  Column<E, F, out RelatedTo<E>> 
	
}
