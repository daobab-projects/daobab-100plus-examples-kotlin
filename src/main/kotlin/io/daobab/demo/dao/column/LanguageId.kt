package io.daobab.demo.dao.column

import io.daobab.creation.DaobabCache
import io.daobab.model.*;

interface LanguageId<E : Entity, F> : RelatedTo<E>, MapHandler<E> {

	fun getLanguageId(): F = readParam("LanguageId")

	fun setLanguageId(value: F): E {
		return storeParam("LanguageId", value)
	}

    /**
     * table:FILM, type:TINYINT, size:8, nullable:false
     * table:LANGUAGE, type:TINYINT, size:8, nullable:false
     */
	fun colLanguageId(): Column<E, F, out RelatedTo<E>> =
		DaobabCache.getColumn("LanguageId", "LANGUAGE_ID", this as Table<*>, Int::class.java) as  Column<E, F, out RelatedTo<E>> 
	
}
