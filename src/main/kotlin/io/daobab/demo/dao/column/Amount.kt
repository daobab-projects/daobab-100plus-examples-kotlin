package io.daobab.demo.dao.column

import io.daobab.creation.DaobabCache
import io.daobab.model.*;
import java.math.BigDecimal
interface Amount<E : Entity, F> : RelatedTo<E>, MapHandler<E> {

	fun getAmount(): F = readParam("Amount")

	fun setAmount(value: F): E {
		return storeParam("Amount", value)
	}

    /**
     * table:PAYMENT, type:DECIMAL, size:5, nullable:false
     */
	fun colAmount(): Column<E, F, out RelatedTo<E>> =
		DaobabCache.getColumn("Amount", "AMOUNT", this as Table<*>, BigDecimal::class.java) as  Column<E, F, out RelatedTo<E>> 
	
}
