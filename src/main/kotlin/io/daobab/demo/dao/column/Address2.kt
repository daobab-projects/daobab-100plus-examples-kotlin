package io.daobab.demo.dao.column

import io.daobab.model.Column
import io.daobab.model.EntityRelationMap
import io.daobab.model.EntityMap



interface Address2<E : EntityMap, F> : EntityRelationMap<E> {

    fun getAddress2(): F = getColumnParam("Address2")
    @Suppress("UNCHECKED_CAST")
    fun setAddress2(value: F): E {
		setColumnParam("Address2", value)
		return this as E
	}
    /**
     * table:ADDRESS,type:VARCHAR,size:50,nullable:true
     */
    fun colAddress2() =
        object : Column<E, F, Address2<*, F>> {
            override fun getColumnName() = "ADDRESS2"
            override fun getFieldName() = "Address2"
            override fun getInstance() = entity
            override fun getFieldClass() = String::class.java
            override fun getValue(entity: Address2<*, F>) = entity.getAddress2()
            override fun hashCode() = toString().hashCode()
            override fun toString() = "$entityName.$fieldName"
            override fun setValue(entity: Address2<*, F>, value: F){
                entity.setAddress2(value)
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
