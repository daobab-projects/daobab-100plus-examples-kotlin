package io.daobab.demo.dao.column

import io.daobab.creation.DaobabCache
import io.daobab.model.*;

interface StaffId<E : Entity, F> : RelatedTo<E>, MapHandler<E> {

	fun getStaffId(): F = readParam("StaffId")

	fun setStaffId(value: F): E {
		return storeParam("StaffId", value)
	}

    /**
     * table:PAYMENT, type:TINYINT, size:8, nullable:false
     * table:RENTAL, type:TINYINT, size:8, nullable:false
     * table:STAFF, type:TINYINT, size:8, nullable:false
     */
	fun colStaffId(): Column<E, F, out RelatedTo<E>> =
		DaobabCache.getColumn("StaffId", "STAFF_ID", this as Table<*>, Int::class.java) as  Column<E, F, out RelatedTo<E>> 
	
}
