package io.daobab.demo.dao.column

import io.daobab.creation.DaobabCache
import io.daobab.model.*;

interface CustomerId<E : Entity, F> : RelatedTo<E>, MapHandler<E> {

	fun getCustomerId(): F = readParam("CustomerId")

	fun setCustomerId(value: F): E {
		return storeParam("CustomerId", value)
	}

    /**
     * table:CUSTOMER, type:SMALLINT, size:16, nullable:false
     * table:PAYMENT, type:SMALLINT, size:16, nullable:false
     * table:RENTAL, type:SMALLINT, size:16, nullable:false
     */
	fun colCustomerId(): Column<E, F, out RelatedTo<E>> =
		DaobabCache.getColumn("CustomerId", "CUSTOMER_ID", this as Table<*>, Int::class.java) as  Column<E, F, out RelatedTo<E>> 
	
}
