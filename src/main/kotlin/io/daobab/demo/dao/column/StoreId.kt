package io.daobab.demo.dao.column

import io.daobab.model.Column
import io.daobab.model.EntityRelationMap
import io.daobab.model.EntityMap



interface StoreId<E : EntityMap, F> : EntityRelationMap<E> {

    fun getStoreId(): F = getColumnParam("StoreId")
    @Suppress("UNCHECKED_CAST")
    fun setStoreId(value: F): E {
		setColumnParam("StoreId", value)
		return this as E
	}
    /**
     * table:CUSTOMER,type:TINYINT,size:8,nullable:false
     * table:INVENTORY,type:TINYINT,size:8,nullable:false
     * table:STAFF,type:TINYINT,size:8,nullable:false
     * table:STORE,type:TINYINT,size:8,nullable:false
     */
    fun colStoreId() =
        object : Column<E, F, StoreId<*, F>> {
            override fun getColumnName() = "STORE_ID"
            override fun getFieldName() = "StoreId"
            override fun getInstance() = entity
            override fun getFieldClass() = Int::class.java
            override fun getValue(entity: StoreId<*, F>) = entity.getStoreId()
            override fun hashCode() = toString().hashCode()
            override fun toString() = "$entityName.$fieldName"
            override fun setValue(entity: StoreId<*, F>, value: F){
                entity.setStoreId(value)
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
