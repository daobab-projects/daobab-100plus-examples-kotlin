package io.daobab.demo.dao.column

import io.daobab.creation.DaobabCache
import io.daobab.model.*;

interface AddressId<E : Entity, F> : RelatedTo<E>, MapHandler<E> {

	fun getAddressId(): F = readParam("AddressId")

	fun setAddressId(value: F): E {
		return storeParam("AddressId", value)
	}

    /**
     * table:ADDRESS, type:SMALLINT, size:16, nullable:false
     * table:CUSTOMER, type:SMALLINT, size:16, nullable:false
     * table:STAFF, type:SMALLINT, size:16, nullable:false
     * table:STORE, type:SMALLINT, size:16, nullable:false
     */
	fun colAddressId(): Column<E, F, out RelatedTo<E>> =
		DaobabCache.getColumn("AddressId", "ADDRESS_ID", this as Table<*>, Int::class.java) as  Column<E, F, out RelatedTo<E>> 
	
}
