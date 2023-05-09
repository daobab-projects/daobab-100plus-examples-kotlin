package io.daobab.demo.dao.column

import io.daobab.model.Column
import io.daobab.model.EntityRelationMap
import io.daobab.model.EntityMap

import java.math.BigDecimal

interface ReplacementCost<E : EntityMap, F> : EntityRelationMap<E> {

    fun getReplacementCost(): F = getColumnParam("ReplacementCost")
    @Suppress("UNCHECKED_CAST")
    fun setReplacementCost(value: F): E {
		setColumnParam("ReplacementCost", value)
		return this as E
	}
    /**
     * table:FILM,type:DECIMAL,size:5,nullable:false
     */
    fun colReplacementCost() =
        object : Column<E, F, ReplacementCost<*, F>> {
            override fun getColumnName() = "REPLACEMENT_COST"
            override fun getFieldName() = "ReplacementCost"
            override fun getInstance() = entity
            override fun getFieldClass() = BigDecimal::class.java
            override fun getValue(entity: ReplacementCost<*, F>) = entity.getReplacementCost()
            override fun hashCode() = toString().hashCode()
            override fun toString() = "$entityName.$fieldName"
            override fun setValue(entity: ReplacementCost<*, F>, value: F){
                entity.setReplacementCost(value)
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
