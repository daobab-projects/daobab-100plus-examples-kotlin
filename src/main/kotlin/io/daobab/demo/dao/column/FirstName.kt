package io.daobab.demo.dao.column

import io.daobab.model.Column
import io.daobab.model.EntityRelationMap
import io.daobab.model.EntityMap



interface FirstName<E : EntityMap, F> : EntityRelationMap<E> {

    fun getFirstName(): F = getColumnParam("FirstName")
    @Suppress("UNCHECKED_CAST")
    fun setFirstName(value: F): E {
		setColumnParam("FirstName", value)
		return this as E
	}
    /**
     * table:ACTOR,type:VARCHAR,size:45,nullable:false
     * table:CUSTOMER,type:VARCHAR,size:45,nullable:false
     * table:STAFF,type:VARCHAR,size:45,nullable:false
     */
    fun colFirstName() =
        object : Column<E, F, FirstName<*, F>> {
            override fun getColumnName() = "FIRST_NAME"
            override fun getFieldName() = "FirstName"
            override fun getInstance() = entity
            override fun getFieldClass() = String::class.java
            override fun getValue(entity: FirstName<*, F>) = entity.getFirstName()
            override fun hashCode() = toString().hashCode()
            override fun toString() = "$entityName.$fieldName"
            override fun setValue(entity: FirstName<*, F>, value: F){
                entity.setFirstName(value)
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
