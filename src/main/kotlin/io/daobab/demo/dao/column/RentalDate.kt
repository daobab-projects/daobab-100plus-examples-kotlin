package io.daobab.demo.dao.column

import io.daobab.model.Column
import io.daobab.model.EntityRelationMap
import io.daobab.model.EntityMap

import java.time.LocalDateTime

interface RentalDate<E : EntityMap, F> : EntityRelationMap<E> {

    fun getRentalDate(): F = getColumnParam("RentalDate")
    @Suppress("UNCHECKED_CAST")
    fun setRentalDate(value: F): E {
		setColumnParam("RentalDate", value)
		return this as E
	}
    /**
     * table:RENTAL,type:TIMESTAMP,size:26,nullable:false
     */
    fun colRentalDate() =
        object : Column<E, F, RentalDate<*, F>> {
            override fun getColumnName() = "RENTAL_DATE"
            override fun getFieldName() = "RentalDate"
            override fun getInstance() = entity
            override fun getFieldClass() = LocalDateTime::class.java
            override fun getValue(entity: RentalDate<*, F>) = entity.getRentalDate()
            override fun hashCode() = toString().hashCode()
            override fun toString() = "$entityName.$fieldName"
            override fun setValue(entity: RentalDate<*, F>, value: F){
                entity.setRentalDate(value)
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
