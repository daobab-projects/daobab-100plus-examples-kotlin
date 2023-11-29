package io.daobab.demo.dao.column

import io.daobab.creation.DaobabCache
import io.daobab.model.*;

interface CountryId<E : Entity, F> : RelatedTo<E>, MapHandler<E> {

	fun getCountryId(): F = readParam("CountryId")

	fun setCountryId(value: F): E {
		return storeParam("CountryId", value)
	}

    /**
     * table:CITY, type:SMALLINT, size:16, nullable:false
     * table:COUNTRY, type:SMALLINT, size:16, nullable:false
     */
	fun colCountryId(): Column<E, F, out RelatedTo<E>> =
		DaobabCache.getColumn("CountryId", "COUNTRY_ID", this as Table<*>, Int::class.java) as  Column<E, F, out RelatedTo<E>> 
	
}
