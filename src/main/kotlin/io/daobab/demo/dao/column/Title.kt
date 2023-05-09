package io.daobab.demo.dao.column

import io.daobab.model.Column
import io.daobab.model.EntityRelationMap
import io.daobab.model.EntityMap



interface Title<E : EntityMap, F> : EntityRelationMap<E> {

    fun getTitle(): F = getColumnParam("Title")
    @Suppress("UNCHECKED_CAST")
    fun setTitle(value: F): E {
		setColumnParam("Title", value)
		return this as E
	}
    /**
     * table:FILM,type:VARCHAR,size:255,nullable:false
     * table:FILM_TEXT,type:VARCHAR,size:255,nullable:false
     */
    fun colTitle() =
        object : Column<E, F, Title<*, F>> {
            override fun getColumnName() = "TITLE"
            override fun getFieldName() = "Title"
            override fun getInstance() = entity
            override fun getFieldClass() = String::class.java
            override fun getValue(entity: Title<*, F>) = entity.getTitle()
            override fun hashCode() = toString().hashCode()
            override fun toString() = "$entityName.$fieldName"
            override fun setValue(entity: Title<*, F>, value: F){
                entity.setTitle(value)
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
