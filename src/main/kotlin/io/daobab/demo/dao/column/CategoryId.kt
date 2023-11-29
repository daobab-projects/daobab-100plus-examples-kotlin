package io.daobab.demo.dao.column

import io.daobab.creation.DaobabCache
import io.daobab.model.*;

interface CategoryId<E : Entity, F> : RelatedTo<E>, MapHandler<E> {

	fun getCategoryId(): F = readParam("CategoryId")

	fun setCategoryId(value: F): E {
		return storeParam("CategoryId", value)
	}

    /**
     * table:CATEGORY, type:TINYINT, size:8, nullable:false
     * table:FILM_CATEGORY, type:TINYINT, size:8, nullable:false
     */
	fun colCategoryId(): Column<E, F, out RelatedTo<E>> =
		DaobabCache.getColumn("CategoryId", "CATEGORY_ID", this as Table<*>, Int::class.java) as  Column<E, F, out RelatedTo<E>> 
	
}
