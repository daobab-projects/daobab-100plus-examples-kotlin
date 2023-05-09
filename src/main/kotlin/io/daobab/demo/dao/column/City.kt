package io.daobab.demo.dao.column

import io.daobab.model.Column
import io.daobab.model.EntityRelationMap
import io.daobab.model.EntityMap



interface City<E : EntityMap, F> : EntityRelationMap<E> {

    fun getCity(): F = getColumnParam("City")
    @Suppress("UNCHECKED_CAST")
    fun setCity(value: F): E {
		setColumnParam("City", value)
		return this as E
	}
    /**
     * table:CITY,type:VARCHAR,size:50,nullable:false
     */
    fun colCity() =
        object : Column<E, F, City<*, F>> {
            override fun getColumnName() = "CITY"
            override fun getFieldName() = "City"
            override fun getInstance() = entity
            override fun getFieldClass() = String::class.java
            override fun getValue(entity: City<*, F>) = entity.getCity()
            override fun hashCode() = toString().hashCode()
            override fun toString() = "$entityName.$fieldName"
            override fun setValue(entity: City<*, F>, value: F){
                entity.setCity(value)
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
