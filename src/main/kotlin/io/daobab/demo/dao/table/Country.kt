package io.daobab.demo.dao.table

import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.JsonInclude
import io.daobab.clone.EntityDuplicator
import io.daobab.model.Column
import io.daobab.model.TableColumn
import io.daobab.model.PrimaryKey
import io.daobab.demo.dao.column.CountryId
import io.daobab.demo.dao.column.LastUpdate

import io.daobab.model.Table

import java.time.LocalDateTime
import java.util.*

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonAutoDetect(fieldVisibility = ANY, getterVisibility = NONE, setterVisibility = NONE)
class Country : Table(), 
	CountryId<Country, Int>,
	io.daobab.demo.dao.column.Country<Country, String>,
	LastUpdate<Country, LocalDateTime>,

	PrimaryKey<Country,Int,CountryId<*, Int>>
	{

	override fun getEntityName() = "COUNTRY"

	override fun columns() = 
		listOf(
			TableColumn(colCountryId()).primaryKey().size(16),
			TableColumn(colCountry()).size(50),
			TableColumn(colLastUpdate()).size(26).scale(6)
		)

	override fun clone(): Country  {
		return EntityDuplicator.cloneEntity(this)
	}

	override fun colID() = colCountryId() 

	override fun hashCode() = Objects.hashCode(id)

    override fun equals(obj: Any?): Boolean {
        if (this === obj) return true
        if (obj == null) return false
        if (javaClass != obj.javaClass) return false
        val other: PrimaryKey<*, *, *> = obj as PrimaryKey<*, *, *>
        return id == other.id
    }



}
