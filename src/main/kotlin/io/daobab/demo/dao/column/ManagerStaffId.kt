package io.daobab.demo.dao.column

import io.daobab.creation.DaobabCache
import io.daobab.model.*;

interface ManagerStaffId<E : Entity, F> : RelatedTo<E>, MapHandler<E> {

	fun getManagerStaffId(): F = readParam("ManagerStaffId")

	fun setManagerStaffId(value: F): E {
		return storeParam("ManagerStaffId", value)
	}

    /**
     * table:STORE, type:TINYINT, size:8, nullable:false
     */
	fun colManagerStaffId(): Column<E, F, out RelatedTo<E>> =
		DaobabCache.getColumn("ManagerStaffId", "MANAGER_STAFF_ID", this as Table<*>, Int::class.java) as  Column<E, F, out RelatedTo<E>> 
	
}
