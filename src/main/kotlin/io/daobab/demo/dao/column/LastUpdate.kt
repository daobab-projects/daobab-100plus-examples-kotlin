package io.daobab.demo.dao.column

import io.daobab.creation.DaobabCache
import io.daobab.model.*;
import java.time.LocalDateTime
interface LastUpdate<E : Entity, F> : RelatedTo<E>, MapHandler<E> {

	fun getLastUpdate(): F = readParam("LastUpdate")

	fun setLastUpdate(value: F): E {
		return storeParam("LastUpdate", value)
	}

    /**
     * table:ACTOR, type:TIMESTAMP, size:26, nullable:false
     * table:ADDRESS, type:TIMESTAMP, size:26, nullable:false
     * table:CATEGORY, type:TIMESTAMP, size:26, nullable:false
     * table:CITY, type:TIMESTAMP, size:26, nullable:false
     * table:COUNTRY, type:TIMESTAMP, size:26, nullable:false
     * table:CUSTOMER, type:TIMESTAMP, size:26, nullable:false
     * table:FILM, type:TIMESTAMP, size:26, nullable:false
     * table:FILM_ACTOR, type:TIMESTAMP, size:26, nullable:false
     * table:FILM_CATEGORY, type:TIMESTAMP, size:26, nullable:false
     * table:INVENTORY, type:TIMESTAMP, size:26, nullable:false
     * table:LANGUAGE, type:TIMESTAMP, size:26, nullable:false
     * table:PAYMENT, type:TIMESTAMP, size:26, nullable:false
     * table:RENTAL, type:TIMESTAMP, size:26, nullable:false
     * table:STAFF, type:TIMESTAMP, size:26, nullable:false
     * table:STORE, type:TIMESTAMP, size:26, nullable:false
     */
	fun colLastUpdate(): Column<E, F, out RelatedTo<E>> =
		DaobabCache.getColumn("LastUpdate", "LAST_UPDATE", this as Table<*>, LocalDateTime::class.java) as  Column<E, F, out RelatedTo<E>> 
	
}
