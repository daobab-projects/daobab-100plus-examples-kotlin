package io.daobab.demo.dao.column

import io.daobab.creation.DaobabCache
import io.daobab.model.*;

interface OriginalLanguageId<E : Entity, F> : RelatedTo<E>, MapHandler<E> {

	fun getOriginalLanguageId(): F = readParam("OriginalLanguageId")

	fun setOriginalLanguageId(value: F): E {
		return storeParam("OriginalLanguageId", value)
	}

    /**
     * table:FILM, type:TINYINT, size:8, nullable:true
     */
	fun colOriginalLanguageId(): Column<E, F, out RelatedTo<E>> =
		DaobabCache.getColumn("OriginalLanguageId", "ORIGINAL_LANGUAGE_ID", this as Table<*>, Int::class.java) as  Column<E, F, out RelatedTo<E>> 
	
}
