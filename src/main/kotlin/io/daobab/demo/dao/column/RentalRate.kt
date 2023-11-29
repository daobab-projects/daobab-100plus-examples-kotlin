package io.daobab.demo.dao.column

import io.daobab.creation.DaobabCache
import io.daobab.model.*;
import java.math.BigDecimal
interface RentalRate<E : Entity, F> : RelatedTo<E>, MapHandler<E> {

	fun getRentalRate(): F = readParam("RentalRate")

	fun setRentalRate(value: F): E {
		return storeParam("RentalRate", value)
	}

    /**
     * table:FILM, type:DECIMAL, size:4, nullable:false
     */
	fun colRentalRate(): Column<E, F, out RelatedTo<E>> =
		DaobabCache.getColumn("RentalRate", "RENTAL_RATE", this as Table<*>, BigDecimal::class.java) as  Column<E, F, out RelatedTo<E>> 
	
}
