package io.daobab.demo.dao.column

import io.daobab.creation.DaobabCache
import io.daobab.model.*;
import java.time.LocalDateTime
interface RentalDate<E : Entity, F> : RelatedTo<E>, MapHandler<E> {

	fun getRentalDate(): F = readParam("RentalDate")

	fun setRentalDate(value: F): E {
		return storeParam("RentalDate", value)
	}

    /**
     * table:RENTAL, type:TIMESTAMP, size:26, nullable:false
     */
	fun colRentalDate(): Column<E, F, out RelatedTo<E>> =
		DaobabCache.getColumn("RentalDate", "RENTAL_DATE", this as Table<*>, LocalDateTime::class.java) as  Column<E, F, out RelatedTo<E>> 
	
}
