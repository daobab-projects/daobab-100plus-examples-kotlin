package io.daobab.demo.dao.column

import io.daobab.model.Column
import io.daobab.model.EntityRelationMap
import io.daobab.model.EntityMap

import java.math.BigDecimal

interface Amount<E : EntityMap, F> : EntityRelationMap<E> {

    fun getAmount(): F = getColumnParam("Amount")
    @Suppress("UNCHECKED_CAST")
    fun setAmount(value: F): E {
		setColumnParam("Amount", value)
		return this as E
	}
    /**
     * table:PAYMENT,type:DECIMAL,size:5,nullable:false
     */
    fun colAmount() =
        object : Column<E, F, Amount<*, F>> {
            override fun getColumnName() = "AMOUNT"
            override fun getFieldName() = "Amount"
            override fun getInstance() = entity
            override fun getFieldClass() = BigDecimal::class.java
            override fun getValue(entity: Amount<*, F>) = entity.getAmount()
            override fun hashCode() = toString().hashCode()
            override fun toString() = "$entityName.$fieldName"
            override fun setValue(entity: Amount<*, F>, value: F){
                entity.setAmount(value)
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
