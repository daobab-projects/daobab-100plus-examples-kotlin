package io.daobab.demo.dao.column

import io.daobab.creation.DaobabCache
import io.daobab.model.*;

interface District<E : Entity, F> : RelatedTo<E>, MapHandler<E> {

	fun getDistrict(): F = readParam("District")

	fun setDistrict(value: F): E {
		return storeParam("District", value)
	}

    /**
     * table:ADDRESS, type:VARCHAR, size:20, nullable:false
     */
	fun colDistrict(): Column<E, F, out RelatedTo<E>> =
		DaobabCache.getColumn("District", "DISTRICT", this as Table<*>, String::class.java) as  Column<E, F, out RelatedTo<E>> 
	
}
