package io.daobab.demo.dao.column

import io.daobab.creation.DaobabCache
import io.daobab.model.*;

interface ActorId<E : Entity, F> : RelatedTo<E>, MapHandler<E> {

	fun getActorId(): F = readParam("ActorId")

	fun setActorId(value: F): E {
		return storeParam("ActorId", value)
	}

    /**
     * table:ACTOR, type:SMALLINT, size:16, nullable:false
     * table:FILM_ACTOR, type:SMALLINT, size:16, nullable:false
     */
	fun colActorId(): Column<E, F, out RelatedTo<E>> =
		DaobabCache.getColumn("ActorId", "ACTOR_ID", this as Table<*>, Int::class.java) as  Column<E, F, out RelatedTo<E>> 
	
}
