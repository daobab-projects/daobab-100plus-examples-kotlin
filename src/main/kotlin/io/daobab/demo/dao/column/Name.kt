package io.daobab.demo.dao.column

import io.daobab.creation.DaobabCache
import io.daobab.model.*;

interface Name<E : Entity, F> : RelatedTo<E>, MapHandler<E> {

	fun getName(): F = readParam("Name")

	fun setName(value: F): E {
		return storeParam("Name", value)
	}

    /**
     * table:CATEGORY, type:VARCHAR, size:25, nullable:false
     * table:LANGUAGE, type:VARCHAR, size:20, nullable:false
     */
	fun colName(): Column<E, F, out RelatedTo<E>> =
		DaobabCache.getColumn("Name", "NAME", this as Table<*>, String::class.java) as  Column<E, F, out RelatedTo<E>> 
	
}
