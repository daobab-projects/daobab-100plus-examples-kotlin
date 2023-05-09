package io.daobab.demo.dao.column

import io.daobab.model.Column
import io.daobab.model.EntityRelationMap
import io.daobab.model.EntityMap



interface Active<E : EntityMap, F> : EntityRelationMap<E> {

    fun getActive(): F = getColumnParam("Active")
    @Suppress("UNCHECKED_CAST")
    fun setActive(value: F): E {
		setColumnParam("Active", value)
		return this as E
	}
    /**
     * table:CUSTOMER,type:BOOLEAN,size:1,nullable:false
     * table:STAFF,type:BOOLEAN,size:1,nullable:false
     */
    fun colActive() =
        object : Column<E, F, Active<*, F>> {
            override fun getColumnName() = "ACTIVE"
            override fun getFieldName() = "Active"
            override fun getInstance() = entity
            override fun getFieldClass() = Boolean::class.java
            override fun getValue(entity: Active<*, F>) = entity.getActive()
            override fun hashCode() = toString().hashCode()
            override fun toString() = "$entityName.$fieldName"
            override fun setValue(entity: Active<*, F>, value: F){
                entity.setActive(value)
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
