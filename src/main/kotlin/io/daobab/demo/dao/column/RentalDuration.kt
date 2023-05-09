package io.daobab.demo.dao.column

import io.daobab.model.Column
import io.daobab.model.EntityRelationMap
import io.daobab.model.EntityMap



interface RentalDuration<E : EntityMap, F> : EntityRelationMap<E> {

    fun getRentalDuration(): F = getColumnParam("RentalDuration")
    @Suppress("UNCHECKED_CAST")
    fun setRentalDuration(value: F): E {
		setColumnParam("RentalDuration", value)
		return this as E
	}
    /**
     * table:FILM,type:TINYINT,size:8,nullable:false
     */
    fun colRentalDuration() =
        object : Column<E, F, RentalDuration<*, F>> {
            override fun getColumnName() = "RENTAL_DURATION"
            override fun getFieldName() = "RentalDuration"
            override fun getInstance() = entity
            override fun getFieldClass() = Int::class.java
            override fun getValue(entity: RentalDuration<*, F>) = entity.getRentalDuration()
            override fun hashCode() = toString().hashCode()
            override fun toString() = "$entityName.$fieldName"
            override fun setValue(entity: RentalDuration<*, F>, value: F){
                entity.setRentalDuration(value)
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
