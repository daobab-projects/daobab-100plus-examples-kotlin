package io.daobab.demo.dao.column

import io.daobab.model.Column
import io.daobab.model.EntityRelationMap
import io.daobab.model.EntityMap

import java.time.LocalDate

interface ReleaseYear<E : EntityMap, F> : EntityRelationMap<E> {

    fun getReleaseYear(): F = getColumnParam("ReleaseYear")
    @Suppress("UNCHECKED_CAST")
    fun setReleaseYear(value: F): E {
		setColumnParam("ReleaseYear", value)
		return this as E
	}
    /**
     * table:FILM,type:DATE,size:10,nullable:true
     */
    fun colReleaseYear() =
        object : Column<E, F, ReleaseYear<*, F>> {
            override fun getColumnName() = "RELEASE_YEAR"
            override fun getFieldName() = "ReleaseYear"
            override fun getInstance() = entity
            override fun getFieldClass() = LocalDate::class.java
            override fun getValue(entity: ReleaseYear<*, F>) = entity.getReleaseYear()
            override fun hashCode() = toString().hashCode()
            override fun toString() = "$entityName.$fieldName"
            override fun setValue(entity: ReleaseYear<*, F>, value: F){
                entity.setReleaseYear(value)
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
