package io.daobab.demo.dao.column

import io.daobab.model.Column
import io.daobab.model.EntityRelationMap
import io.daobab.model.EntityMap



interface CustomerId<E : EntityMap, F> : EntityRelationMap<E> {

    fun getCustomerId(): F = getColumnParam("CustomerId")
    @Suppress("UNCHECKED_CAST")
    fun setCustomerId(value: F): E {
		setColumnParam("CustomerId", value)
		return this as E
	}
    /**
     * table:CUSTOMER,type:SMALLINT,size:16,nullable:false
     * table:PAYMENT,type:SMALLINT,size:16,nullable:false
     * table:RENTAL,type:SMALLINT,size:16,nullable:false
     */
    fun colCustomerId() =
        object : Column<E, F, CustomerId<*, F>> {
            override fun getColumnName() = "CUSTOMER_ID"
            override fun getFieldName() = "CustomerId"
            override fun getInstance() = entity
            override fun getFieldClass() = Int::class.java
            override fun getValue(entity: CustomerId<*, F>) = entity.getCustomerId()
            override fun hashCode() = toString().hashCode()
            override fun toString() = "$entityName.$fieldName"
            override fun setValue(entity: CustomerId<*, F>, value: F){
                entity.setCustomerId(value)
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
