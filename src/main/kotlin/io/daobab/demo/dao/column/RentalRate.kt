package io.daobab.demo.dao.column

import io.daobab.model.Column
import io.daobab.model.EntityRelationMap
import io.daobab.model.EntityMap

import java.math.BigDecimal

interface RentalRate<E : EntityMap, F> : EntityRelationMap<E> {

    fun getRentalRate(): F = getColumnParam("RentalRate")
    @Suppress("UNCHECKED_CAST")
    fun setRentalRate(value: F): E {
		setColumnParam("RentalRate", value)
		return this as E
	}
    /**
     * table:FILM,type:DECIMAL,size:4,nullable:false
     */
    fun colRentalRate() =
        object : Column<E, F, RentalRate<*, F>> {
            override fun getColumnName() = "RENTAL_RATE"
            override fun getFieldName() = "RentalRate"
            override fun getInstance() = entity
            override fun getFieldClass() = BigDecimal::class.java
            override fun getValue(entity: RentalRate<*, F>) = entity.getRentalRate()
            override fun hashCode() = toString().hashCode()
            override fun toString() = "$entityName.$fieldName"
            override fun setValue(entity: RentalRate<*, F>, value: F){
                entity.setRentalRate(value)
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
