package io.daobab.demo.dao.column

import io.daobab.creation.DaobabCache
import io.daobab.model.*;

interface SpecialFeatures<E : Entity, F> : RelatedTo<E>, MapHandler<E> {

	fun getSpecialFeatures(): F = readParam("SpecialFeatures")

	fun setSpecialFeatures(value: F): E {
		return storeParam("SpecialFeatures", value)
	}

    /**
     * table:FILM, type:VARCHAR, size:54, nullable:true
     */
	fun colSpecialFeatures(): Column<E, F, out RelatedTo<E>> =
		DaobabCache.getColumn("SpecialFeatures", "SPECIAL_FEATURES", this as Table<*>, String::class.java) as  Column<E, F, out RelatedTo<E>> 
	
}
