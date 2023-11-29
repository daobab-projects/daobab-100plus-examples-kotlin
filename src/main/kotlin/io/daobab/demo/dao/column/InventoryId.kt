package io.daobab.demo.dao.column

import io.daobab.creation.DaobabCache
import io.daobab.model.*;
import java.math.BigDecimal
interface InventoryId<E : Entity, F> : RelatedTo<E>, MapHandler<E> {

	fun getInventoryId(): F = readParam("InventoryId")

	fun setInventoryId(value: F): E {
		return storeParam("InventoryId", value)
	}

    /**
     * table:INVENTORY, type:INTEGER, size:32, nullable:false
     * table:RENTAL, type:INTEGER, size:32, nullable:false
     */
	fun colInventoryId(): Column<E, F, out RelatedTo<E>> =
		DaobabCache.getColumn("InventoryId", "INVENTORY_ID", this as Table<*>, BigDecimal::class.java) as  Column<E, F, out RelatedTo<E>> 
	
}
