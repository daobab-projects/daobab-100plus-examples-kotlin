package io.daobab.demo.dao.column

import io.daobab.model.Column
import io.daobab.model.EntityRelationMap
import io.daobab.model.EntityMap



interface Address<E : EntityMap, F> : EntityRelationMap<E> {

    fun getAddress(): F = getColumnParam("Address")
    @Suppress("UNCHECKED_CAST")
    fun setAddress(value: F): E {
		setColumnParam("Address", value)
		return this as E
	}
    /**
     * table:ADDRESS,type:VARCHAR,size:50,nullable:false
     */
    fun colAddress() =
        object : Column<E, F, Address<*, F>> {
            override fun getColumnName() = "ADDRESS"
            override fun getFieldName() = "Address"
            override fun getInstance() = entity
            override fun getFieldClass() = String::class.java
            override fun getValue(entity: Address<*, F>) = entity.getAddress()
            override fun hashCode() = toString().hashCode()
            override fun toString() = "$entityName.$fieldName"
            override fun setValue(entity: Address<*, F>, value: F){
                entity.setAddress(value)
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
