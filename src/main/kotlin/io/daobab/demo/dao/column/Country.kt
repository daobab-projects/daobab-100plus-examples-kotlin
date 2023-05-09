package io.daobab.demo.dao.column

import io.daobab.model.Column
import io.daobab.model.EntityRelationMap
import io.daobab.model.EntityMap



interface Country<E : EntityMap, F> : EntityRelationMap<E> {

    fun getCountry(): F = getColumnParam("Country")
    @Suppress("UNCHECKED_CAST")
    fun setCountry(value: F): E {
		setColumnParam("Country", value)
		return this as E
	}
    /**
     * table:COUNTRY,type:VARCHAR,size:50,nullable:false
     */
    fun colCountry() =
        object : Column<E, F, Country<*, F>> {
            override fun getColumnName() = "COUNTRY"
            override fun getFieldName() = "Country"
            override fun getInstance() = entity
            override fun getFieldClass() = String::class.java
            override fun getValue(entity: Country<*, F>) = entity.getCountry()
            override fun hashCode() = toString().hashCode()
            override fun toString() = "$entityName.$fieldName"
            override fun setValue(entity: Country<*, F>, value: F){
                entity.setCountry(value)
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
