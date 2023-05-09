package io.daobab.demo.dao.column

import io.daobab.model.Column
import io.daobab.model.EntityRelationMap
import io.daobab.model.EntityMap



interface FilmId<E : EntityMap, F> : EntityRelationMap<E> {

    fun getFilmId(): F = getColumnParam("FilmId")
    @Suppress("UNCHECKED_CAST")
    fun setFilmId(value: F): E {
		setColumnParam("FilmId", value)
		return this as E
	}
    /**
     * table:FILM,type:SMALLINT,size:16,nullable:false
     * table:FILM_ACTOR,type:SMALLINT,size:16,nullable:false
     * table:FILM_CATEGORY,type:SMALLINT,size:16,nullable:false
     * table:FILM_TEXT,type:SMALLINT,size:16,nullable:false
     * table:INVENTORY,type:SMALLINT,size:16,nullable:false
     */
    fun colFilmId() =
        object : Column<E, F, FilmId<*, F>> {
            override fun getColumnName() = "FILM_ID"
            override fun getFieldName() = "FilmId"
            override fun getInstance() = entity
            override fun getFieldClass() = Int::class.java
            override fun getValue(entity: FilmId<*, F>) = entity.getFilmId()
            override fun hashCode() = toString().hashCode()
            override fun toString() = "$entityName.$fieldName"
            override fun setValue(entity: FilmId<*, F>, value: F){
                entity.setFilmId(value)
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
