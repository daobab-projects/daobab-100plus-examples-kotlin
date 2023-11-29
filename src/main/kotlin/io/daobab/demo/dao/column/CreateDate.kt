package io.daobab.demo.dao.column

import io.daobab.creation.DaobabCache
import io.daobab.model.*;
import java.time.LocalDateTime
interface CreateDate<E : Entity, F> : RelatedTo<E>, MapHandler<E> {

	fun getCreateDate(): F = readParam("CreateDate")

	fun setCreateDate(value: F): E {
		return storeParam("CreateDate", value)
	}

    /**
     * table:CUSTOMER, type:TIMESTAMP, size:26, nullable:false
     */
	fun colCreateDate(): Column<E, F, out RelatedTo<E>> =
		DaobabCache.getColumn("CreateDate", "CREATE_DATE", this as Table<*>, LocalDateTime::class.java) as  Column<E, F, out RelatedTo<E>> 
	
}
