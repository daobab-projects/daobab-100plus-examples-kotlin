package io.daobab.demo.dao.column

import io.daobab.model.Column
import io.daobab.model.EntityRelationMap
import io.daobab.model.EntityMap



interface Length<E : EntityMap, F> : EntityRelationMap<E> {

    fun getLength(): F = getColumnParam("Length")
    @Suppress("UNCHECKED_CAST")
    fun setLength(value: F): E {
		setColumnParam("Length", value)
		return this as E
	}
    /**
     * table:FILM,type:SMALLINT,size:16,nullable:true
     */
    fun colLength() =
        object : Column<E, F, Length<*, F>> {
            override fun getColumnName() = "LENGTH"
            override fun getFieldName() = "Length"
            override fun getInstance() = entity
            override fun getFieldClass() = Int::class.java
            override fun getValue(entity: Length<*, F>) = entity.getLength()
            override fun hashCode() = toString().hashCode()
            override fun toString() = "$entityName.$fieldName"
            override fun setValue(entity: Length<*, F>, value: F){
                entity.setLength(value)
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
