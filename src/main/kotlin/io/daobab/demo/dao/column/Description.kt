package io.daobab.demo.dao.column

import io.daobab.model.Column
import io.daobab.model.EntityRelationMap
import io.daobab.model.EntityMap



interface Description<E : EntityMap, F> : EntityRelationMap<E> {

    fun getDescription(): F = getColumnParam("Description")
    @Suppress("UNCHECKED_CAST")
    fun setDescription(value: F): E {
		setColumnParam("Description", value)
		return this as E
	}
    /**
     * table:FILM,type:VARCHAR,size:1000000000,nullable:true
     * table:FILM_TEXT,type:VARCHAR,size:1000000000,nullable:true
     */
    fun colDescription() =
        object : Column<E, F, Description<*, F>> {
            override fun getColumnName() = "DESCRIPTION"
            override fun getFieldName() = "Description"
            override fun getInstance() = entity
            override fun getFieldClass() = String::class.java
            override fun getValue(entity: Description<*, F>) = entity.getDescription()
            override fun hashCode() = toString().hashCode()
            override fun toString() = "$entityName.$fieldName"
            override fun setValue(entity: Description<*, F>, value: F){
                entity.setDescription(value)
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
