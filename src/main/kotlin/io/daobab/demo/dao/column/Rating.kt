package io.daobab.demo.dao.column

import io.daobab.model.Column
import io.daobab.model.EntityRelationMap
import io.daobab.model.EntityMap



interface Rating<E : EntityMap, F> : EntityRelationMap<E> {

    fun getRating(): F = getColumnParam("Rating")
    @Suppress("UNCHECKED_CAST")
    fun setRating(value: F): E {
		setColumnParam("Rating", value)
		return this as E
	}
    /**
     * table:FILM,type:VARCHAR,size:5,nullable:true
     */
    fun colRating() =
        object : Column<E, F, Rating<*, F>> {
            override fun getColumnName() = "RATING"
            override fun getFieldName() = "Rating"
            override fun getInstance() = entity
            override fun getFieldClass() = String::class.java
            override fun getValue(entity: Rating<*, F>) = entity.getRating()
            override fun hashCode() = toString().hashCode()
            override fun toString() = "$entityName.$fieldName"
            override fun setValue(entity: Rating<*, F>, value: F){
                entity.setRating(value)
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
