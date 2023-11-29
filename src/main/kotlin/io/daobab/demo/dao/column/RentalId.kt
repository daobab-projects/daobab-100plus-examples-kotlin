package io.daobab.demo.dao.column

import io.daobab.creation.DaobabCache
import io.daobab.model.*;
import java.math.BigDecimal
interface RentalId<E : Entity, F> : RelatedTo<E>, MapHandler<E> {

	fun getRentalId(): F = readParam("RentalId")

	fun setRentalId(value: F): E {
		return storeParam("RentalId", value)
	}

    /**
     * table:PAYMENT, type:INTEGER, size:32, nullable:true
     * table:RENTAL, type:INTEGER, size:32, nullable:false
     */
	fun colRentalId(): Column<E, F, out RelatedTo<E>> =
		DaobabCache.getColumn("RentalId", "RENTAL_ID", this as Table<*>, BigDecimal::class.java) as  Column<E, F, out RelatedTo<E>> 
	
}
