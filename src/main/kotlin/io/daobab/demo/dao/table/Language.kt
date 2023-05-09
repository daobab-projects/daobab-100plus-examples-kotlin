package io.daobab.demo.dao.table

import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE
import com.fasterxml.jackson.annotation.JsonInclude
import io.daobab.clone.EntityDuplicator
import io.daobab.demo.dao.Lang
import io.daobab.demo.dao.column.LanguageId
import io.daobab.demo.dao.column.LastUpdate
import io.daobab.demo.dao.column.Name
import io.daobab.model.PrimaryKey
import io.daobab.model.Table
import io.daobab.model.TableColumn
import java.time.LocalDateTime
import java.util.*

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonAutoDetect(fieldVisibility = ANY, getterVisibility = NONE, setterVisibility = NONE)
class Language : Table(), 
	LanguageId<Language, Int>,
	Name<Language, Lang>,
	LastUpdate<Language, LocalDateTime>,

	PrimaryKey<Language,Int,LanguageId<*, Int>>
	{

	override fun getEntityName() = "LANGUAGE"

	override fun columns() = 
		listOf(
			TableColumn(colLanguageId()).primaryKey().size(8),
			TableColumn(colName()).size(20),
			TableColumn(colLastUpdate()).size(26).scale(6)
		)

	override fun clone(): Language  {
		return EntityDuplicator.cloneEntity(this)
	}

	override fun colID() = colLanguageId() 

	override fun hashCode() = Objects.hashCode(id)

    override fun equals(obj: Any?): Boolean {
        if (this === obj) return true
        if (obj == null) return false
        if (javaClass != obj.javaClass) return false
        val other: PrimaryKey<*, *, *> = obj as PrimaryKey<*, *, *>
        return id == other.id
    }



}
