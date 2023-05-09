package io.daobab.demo.dao.table

import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE
import com.fasterxml.jackson.annotation.JsonInclude
import io.daobab.clone.EntityDuplicator
import io.daobab.demo.dao.column.CityId
import io.daobab.demo.dao.column.CountryId
import io.daobab.demo.dao.column.LastUpdate
import io.daobab.model.PrimaryKey
import io.daobab.model.Table
import io.daobab.model.TableColumn
import java.time.LocalDateTime
import java.util.*

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonAutoDetect(fieldVisibility = ANY, getterVisibility = NONE, setterVisibility = NONE)
open class City : Table(),
	CityId<City, Int>,
	io.daobab.demo.dao.column.City<City, String>,
	CountryId<City, Int>,
	LastUpdate<City, LocalDateTime>,

	PrimaryKey<City,Int,CityId<*, Int>>
	{

	override fun getEntityName() = "CITY"

	override fun columns() = 
		listOf(
			TableColumn(colCityId()).primaryKey().size(16),
			TableColumn(colCity()).size(50),
			TableColumn(colCountryId()).size(16),
			TableColumn(colLastUpdate()).size(26).scale(6)
		)

	override fun clone(): City  {
		return EntityDuplicator.cloneEntity(this)
	}

	override fun colID() = colCityId() 

	override fun hashCode() = Objects.hashCode(id)

    override fun equals(obj: Any?): Boolean {
        if (this === obj) return true
        if (obj == null) return false
        if (javaClass != obj.javaClass) return false
        val other: PrimaryKey<*, *, *> = obj as PrimaryKey<*, *, *>
        return id == other.id
    }



}
