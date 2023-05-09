package io.daobab.demo.dao.column

import io.daobab.model.Column
import io.daobab.model.EntityMap
import io.daobab.model.EntityRelationMap



interface AddressId<E : EntityMap, F> : EntityRelationMap<E> {

    fun getAddressId(): F = getColumnParam("AddressId")
    @Suppress("UNCHECKED_CAST")
    fun setAddressId(value: F): E {
		setColumnParam("AddressId", value)
		return this as E
	}
    /**
     * table:ADDRESS,type:SMALLINT,size:16,nullable:false
     * table:CUSTOMER,type:SMALLINT,size:16,nullable:false
     * table:STAFF,type:SMALLINT,size:16,nullable:false
     * table:STORE,type:SMALLINT,size:16,nullable:false
     */
    fun colAddressId() =
        object : Column<E, F, AddressId<*, F>> {
            override fun getColumnName() = "ADDRESS_ID"
            override fun getFieldName() = "AddressId"
            override fun getInstance() = entity
            override fun getFieldClass() = Int::class.java
            override fun getValue(entity: AddressId<*, F>) = entity.getAddressId()
            override fun hashCode() = toString().hashCode()
            override fun toString() = "$entityName.$fieldName"
            override fun setValue(entity: AddressId<*, F>, value: F){
                entity.setAddressId(value)
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
