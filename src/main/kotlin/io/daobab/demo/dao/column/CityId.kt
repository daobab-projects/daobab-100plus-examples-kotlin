package io.daobab.demo.dao.column

import io.daobab.creation.DaobabCache
import io.daobab.model.*;

interface CityId<E : Entity, F> : RelatedTo<E>, MapHandler<E> {

	fun getCityId(): F = readParam("CityId")

	fun setCityId(value: F): E {
		return storeParam("CityId", value)
	}

    /**
     * table:ADDRESS, type:SMALLINT, size:16, nullable:false
     * table:CITY, type:SMALLINT, size:16, nullable:false
     */
	fun colCityId(): Column<E, F, out RelatedTo<E>> =
		DaobabCache.getColumn("CityId", "CITY_ID", this as Table<*>, Int::class.java) as  Column<E, F, out RelatedTo<E>> 
	
}
