package io.daobab.demo.dao.column

import io.daobab.model.Column
import io.daobab.model.EntityRelationMap
import io.daobab.model.EntityMap



interface LastName<E : EntityMap, F> : EntityRelationMap<E> {

    fun getLastName(): F = getColumnParam("LastName")
    @Suppress("UNCHECKED_CAST")
    fun setLastName(value: F): E {
		setColumnParam("LastName", value)
		return this as E
	}
    /**
     * table:ACTOR,type:VARCHAR,size:45,nullable:false
     * table:CUSTOMER,type:VARCHAR,size:45,nullable:false
     * table:STAFF,type:VARCHAR,size:45,nullable:false
     */
    fun colLastName() =
        object : Column<E, F, LastName<*, F>> {
            override fun getColumnName() = "LAST_NAME"
            override fun getFieldName() = "LastName"
            override fun getInstance() = entity
            override fun getFieldClass() = String::class.java
            override fun getValue(entity: LastName<*, F>) = entity.getLastName()
            override fun hashCode() = toString().hashCode()
            override fun toString() = "$entityName.$fieldName"
            override fun setValue(entity: LastName<*, F>, value: F){
                entity.setLastName(value)
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
