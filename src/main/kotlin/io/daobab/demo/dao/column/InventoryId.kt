package io.daobab.demo.dao.column

import io.daobab.model.Column
import io.daobab.model.EntityRelationMap
import io.daobab.model.EntityMap

import java.math.BigDecimal

interface InventoryId<E : EntityMap, F> : EntityRelationMap<E> {

    fun getInventoryId(): F = getColumnParam("InventoryId")
    @Suppress("UNCHECKED_CAST")
    fun setInventoryId(value: F): E {
		setColumnParam("InventoryId", value)
		return this as E
	}
    /**
     * table:INVENTORY,type:INTEGER,size:32,nullable:false
     * table:RENTAL,type:INTEGER,size:32,nullable:false
     */
    fun colInventoryId() =
        object : Column<E, F, InventoryId<*, F>> {
            override fun getColumnName() = "INVENTORY_ID"
            override fun getFieldName() = "InventoryId"
            override fun getInstance() = entity
            override fun getFieldClass() = BigDecimal::class.java
            override fun getValue(entity: InventoryId<*, F>) = entity.getInventoryId()
            override fun hashCode() = toString().hashCode()
            override fun toString() = "$entityName.$fieldName"
            override fun setValue(entity: InventoryId<*, F>, value: F){
                entity.setInventoryId(value)
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
