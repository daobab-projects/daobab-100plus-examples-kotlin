package io.daobab.demo.dao.column

import io.daobab.creation.DaobabCache
import io.daobab.model.*;
import java.math.BigDecimal
interface ReplacementCost<E : Entity, F> : RelatedTo<E>, MapHandler<E> {

	fun getReplacementCost(): F = readParam("ReplacementCost")

	fun setReplacementCost(value: F): E {
		return storeParam("ReplacementCost", value)
	}

    /**
     * table:FILM, type:DECIMAL, size:5, nullable:false
     */
	fun colReplacementCost(): Column<E, F, out RelatedTo<E>> =
		DaobabCache.getColumn("ReplacementCost", "REPLACEMENT_COST", this as Table<*>, BigDecimal::class.java) as  Column<E, F, out RelatedTo<E>> 
	
}
