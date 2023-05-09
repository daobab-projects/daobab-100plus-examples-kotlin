package io.daobab.demo.dao.column

import io.daobab.model.Column
import io.daobab.model.EntityRelationMap
import io.daobab.model.EntityMap

import java.time.LocalDateTime

interface PaymentDate<E : EntityMap, F> : EntityRelationMap<E> {

    fun getPaymentDate(): F = getColumnParam("PaymentDate")
    @Suppress("UNCHECKED_CAST")
    fun setPaymentDate(value: F): E {
		setColumnParam("PaymentDate", value)
		return this as E
	}
    /**
     * table:PAYMENT,type:TIMESTAMP,size:26,nullable:false
     */
    fun colPaymentDate() =
        object : Column<E, F, PaymentDate<*, F>> {
            override fun getColumnName() = "PAYMENT_DATE"
            override fun getFieldName() = "PaymentDate"
            override fun getInstance() = entity
            override fun getFieldClass() = LocalDateTime::class.java
            override fun getValue(entity: PaymentDate<*, F>) = entity.getPaymentDate()
            override fun hashCode() = toString().hashCode()
            override fun toString() = "$entityName.$fieldName"
            override fun setValue(entity: PaymentDate<*, F>, value: F){
                entity.setPaymentDate(value)
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
