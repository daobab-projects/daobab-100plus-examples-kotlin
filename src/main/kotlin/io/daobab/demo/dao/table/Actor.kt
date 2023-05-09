package io.daobab.demo.dao.table

import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE
import com.fasterxml.jackson.annotation.JsonInclude
import io.daobab.clone.EntityDuplicator
import io.daobab.demo.dao.column.ActorId
import io.daobab.demo.dao.column.FirstName
import io.daobab.demo.dao.column.LastName
import io.daobab.demo.dao.column.LastUpdate
import io.daobab.model.PrimaryKey
import io.daobab.model.Table
import io.daobab.model.TableColumn
import java.time.LocalDateTime
import java.util.*

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonAutoDetect(fieldVisibility = ANY, getterVisibility = NONE, setterVisibility = NONE)
class Actor : Table(), 
	ActorId<Actor, Int>,
	FirstName<Actor, String>,
	LastName<Actor, String>,
	LastUpdate<Actor, LocalDateTime>,

	PrimaryKey<Actor,Int,ActorId<*, Int>>
	{

	override fun getEntityName() = "ACTOR"

	override fun columns() = 
		listOf(
			TableColumn(colActorId()).primaryKey().size(16),
			TableColumn(colFirstName()).size(45),
			TableColumn(colLastName()).size(45),
			TableColumn(colLastUpdate()).size(26).scale(6)
		)

	override fun clone(): Actor  {
		return EntityDuplicator.cloneEntity(this)
	}

	override fun colID() = colActorId() 

	override fun hashCode() = Objects.hashCode(id)

    override fun equals(obj: Any?): Boolean {
        if (this === obj) return true
        if (obj == null) return false
        if (javaClass != obj.javaClass) return false
        val other: PrimaryKey<*, *, *> = obj as PrimaryKey<*, *, *>
        return id == other.id
    }



}
