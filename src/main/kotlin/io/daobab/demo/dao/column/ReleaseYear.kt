package io.daobab.demo.dao.column

import io.daobab.creation.DaobabCache
import io.daobab.model.*;
import java.time.LocalDate
interface ReleaseYear<E : Entity, F> : RelatedTo<E>, MapHandler<E> {

	fun getReleaseYear(): F = readParam("ReleaseYear")

	fun setReleaseYear(value: F): E {
		return storeParam("ReleaseYear", value)
	}

    /**
     * table:FILM, type:DATE, size:10, nullable:true
     */
	fun colReleaseYear(): Column<E, F, out RelatedTo<E>> =
		DaobabCache.getColumn("ReleaseYear", "RELEASE_YEAR", this as Table<*>, LocalDate::class.java) as  Column<E, F, out RelatedTo<E>> 
	
}
