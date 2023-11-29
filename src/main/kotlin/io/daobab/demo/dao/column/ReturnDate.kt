package io.daobab.demo.dao.column

import io.daobab.creation.DaobabCache
import io.daobab.model.*;
import java.time.LocalDateTime
interface ReturnDate<E : Entity, F> : RelatedTo<E>, MapHandler<E> {

	fun getReturnDate(): F = readParam("ReturnDate")

	fun setReturnDate(value: F): E {
		return storeParam("ReturnDate", value)
	}

    /**
     * table:RENTAL, type:TIMESTAMP, size:26, nullable:true
     */
	fun colReturnDate(): Column<E, F, out RelatedTo<E>> =
		DaobabCache.getColumn("ReturnDate", "RETURN_DATE", this as Table<*>, LocalDateTime::class.java) as  Column<E, F, out RelatedTo<E>> 
	
}
