package io.daobab.demo.dao.table

import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.JsonInclude
import io.daobab.clone.EntityDuplicator
import io.daobab.model.Column
import io.daobab.model.TableColumn
import io.daobab.model.PrimaryKey
import io.daobab.demo.dao.column.FilmId
import io.daobab.demo.dao.column.Title
import io.daobab.demo.dao.column.Description

import io.daobab.model.Table


import java.util.*

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonAutoDetect(fieldVisibility = ANY, getterVisibility = NONE, setterVisibility = NONE)
class FilmText : Table(), 
	FilmId<FilmText, Int>,
	Title<FilmText, String>,
	Description<FilmText, String?>,

	PrimaryKey<FilmText,Int,FilmId<*, Int>>
	{

	override fun getEntityName() = "FILM_TEXT"

	override fun columns() = 
		listOf(
			TableColumn(colFilmId()).primaryKey().size(16),
			TableColumn(colTitle()).size(255),
			TableColumn(colDescription()).size(1000000000)
		)

	override fun clone(): FilmText  {
		return EntityDuplicator.cloneEntity(this)
	}

	override fun colID() = colFilmId() 

	override fun hashCode() = Objects.hashCode(id)

    override fun equals(obj: Any?): Boolean {
        if (this === obj) return true
        if (obj == null) return false
        if (javaClass != obj.javaClass) return false
        val other: PrimaryKey<*, *, *> = obj as PrimaryKey<*, *, *>
        return id == other.id
    }



}
