package io.daobab.demo.dao.column

import io.daobab.model.Column
import io.daobab.model.EntityRelationMap
import io.daobab.model.EntityMap

import java.math.BigDecimal

interface RentalId<E : EntityMap, F> : EntityRelationMap<E> {

    fun getRentalId(): F = getColumnParam("RentalId")
    @Suppress("UNCHECKED_CAST")
    fun setRentalId(value: F): E {
		setColumnParam("RentalId", value)
		return this as E
	}
    /**
     * table:PAYMENT,type:INTEGER,size:32,nullable:true
     * table:RENTAL,type:INTEGER,size:32,nullable:false
     */
    fun colRentalId() =
        object : Column<E, F, RentalId<*, F>> {
            override fun getColumnName() = "RENTAL_ID"
            override fun getFieldName() = "RentalId"
            override fun getInstance() = entity
            override fun getFieldClass() = BigDecimal::class.java
            override fun getValue(entity: RentalId<*, F>) = entity.getRentalId()
            override fun hashCode() = toString().hashCode()
            override fun toString() = "$entityName.$fieldName"
            override fun setValue(entity: RentalId<*, F>, value: F){
                entity.setRentalId(value)
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
