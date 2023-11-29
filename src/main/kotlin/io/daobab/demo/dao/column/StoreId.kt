package io.daobab.demo.dao.column

import io.daobab.creation.DaobabCache
import io.daobab.model.*;

interface StoreId<E : Entity, F> : RelatedTo<E>, MapHandler<E> {

	fun getStoreId(): F = readParam("StoreId")

	fun setStoreId(value: F): E {
		return storeParam("StoreId", value)
	}

    /**
     * table:CUSTOMER, type:TINYINT, size:8, nullable:false
     * table:INVENTORY, type:TINYINT, size:8, nullable:false
     * table:STAFF, type:TINYINT, size:8, nullable:false
     * table:STORE, type:TINYINT, size:8, nullable:false
     */
	fun colStoreId(): Column<E, F, out RelatedTo<E>> =
		DaobabCache.getColumn("StoreId", "STORE_ID", this as Table<*>, Int::class.java) as  Column<E, F, out RelatedTo<E>> 
	
}
