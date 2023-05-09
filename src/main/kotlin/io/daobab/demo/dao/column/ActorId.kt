package io.daobab.demo.dao.column

import io.daobab.model.Column
import io.daobab.model.EntityRelationMap
import io.daobab.model.EntityMap



interface ActorId<E : EntityMap, F> : EntityRelationMap<E> {

    fun getActorId(): F = getColumnParam("ActorId")
    @Suppress("UNCHECKED_CAST")
    fun setActorId(value: F): E {
		setColumnParam("ActorId", value)
		return this as E
	}
    /**
     * table:ACTOR,type:SMALLINT,size:16,nullable:false
     * table:FILM_ACTOR,type:SMALLINT,size:16,nullable:false
     */
    fun colActorId() =
        object : Column<E, F, ActorId<*, F>> {
            override fun getColumnName() = "ACTOR_ID"
            override fun getFieldName() = "ActorId"
            override fun getInstance() = entity
            override fun getFieldClass() = Int::class.java
            override fun getValue(entity: ActorId<*, F>) = entity.getActorId()
            override fun hashCode() = toString().hashCode()
            override fun toString() = "$entityName.$fieldName"
            override fun setValue(entity: ActorId<*, F>, value: F){
                entity.setActorId(value)
            }
            override fun equals(other: Any?): Boolean {
                if (this === other) return true
                if (other == null) return false
                if (javaClass != other.javaClass) return false
                val otherColumn = other as Column<*, *, *>
                return hashCode() == otherColumn.hashCode()
            }
        }
    }
