package io.daobab.demo.dao.column

import io.daobab.creation.DaobabCache
import io.daobab.model.*;

interface Picture<E : Entity, F> : RelatedTo<E>, MapHandler<E> {

	fun getPicture(): F = readParam("Picture")

	fun setPicture(value: F): E {
		return storeParam("Picture", value)
	}

    /**
     * table:STAFF, type:VARBINARY, size:1000000000, nullable:true
     */
	fun colPicture(): Column<E, F, out RelatedTo<E>> =
		DaobabCache.getColumn("Picture", "PICTURE", this as Table<*>, ByteArray::class.java) as  Column<E, F, out RelatedTo<E>> 
	
}
