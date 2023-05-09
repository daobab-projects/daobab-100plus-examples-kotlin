package io.daobab.demo.dao.column

import io.daobab.model.Column
import io.daobab.model.EntityRelationMap
import io.daobab.model.EntityMap



interface District<E : EntityMap, F> : EntityRelationMap<E> {

    fun getDistrict(): F = getColumnParam("District")
    @Suppress("UNCHECKED_CAST")
    fun setDistrict(value: F): E {
		setColumnParam("District", value)
		return this as E
	}
    /**
     * table:ADDRESS,type:VARCHAR,size:20,nullable:false
     */
    fun colDistrict() =
        object : Column<E, F, District<*, F>> {
            override fun getColumnName() = "DISTRICT"
            override fun getFieldName() = "District"
            override fun getInstance() = entity
            override fun getFieldClass() = String::class.java
            override fun getValue(entity: District<*, F>) = entity.getDistrict()
            override fun hashCode() = toString().hashCode()
            override fun toString() = "$entityName.$fieldName"
            override fun setValue(entity: District<*, F>, value: F){
                entity.setDistrict(value)
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
