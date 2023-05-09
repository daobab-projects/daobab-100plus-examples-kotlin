package io.daobab.demo.model

import io.daobab.demo.dao.table.Actor
import io.daobab.model.EntityMap
import io.daobab.model.EntityRelationMap

interface ActorLazy<E : EntityMap?> : EntityRelationMap<E> {
    /**
     * db name: ACTOR_ID,
     * db type: SMALLINT
     */
    val actor: Actor?
        get() = getColumnParam("Actor")

    fun setActor(`val`: Actor?): E {
        setColumnParam("Actor", `val`)
        return this as E
    }
}
