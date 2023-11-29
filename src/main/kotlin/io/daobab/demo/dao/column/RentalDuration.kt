package io.daobab.demo.dao.column

import io.daobab.creation.DaobabCache
import io.daobab.model.*;

interface RentalDuration<E : Entity, F> : RelatedTo<E>, MapHandler<E> {

	fun getRentalDuration(): F = readParam("RentalDuration")

	fun setRentalDuration(value: F): E {
		return storeParam("RentalDuration", value)
	}

    /**
     * table:FILM, type:TINYINT, size:8, nullable:false
     */
	fun colRentalDuration(): Column<E, F, out RelatedTo<E>> =
		DaobabCache.getColumn("RentalDuration", "RENTAL_DURATION", this as Table<*>, Int::class.java) as  Column<E, F, out RelatedTo<E>> 
	
}
