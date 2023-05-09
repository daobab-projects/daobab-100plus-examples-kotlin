package io.daobab.demo.dao.column

import io.daobab.model.Column
import io.daobab.model.EntityRelationMap
import io.daobab.model.EntityMap



interface PaymentId<E : EntityMap, F> : EntityRelationMap<E> {

    fun getPaymentId(): F = getColumnParam("PaymentId")
    @Suppress("UNCHECKED_CAST")
    fun setPaymentId(value: F): E {
		setColumnParam("PaymentId", value)
		return this as E
	}
    /**
     * table:PAYMENT,type:SMALLINT,size:16,nullable:false
     */
    fun colPaymentId() =
        object : Column<E, F, PaymentId<*, F>> {
            override fun getColumnName() = "PAYMENT_ID"
            override fun getFieldName() = "PaymentId"
            override fun getInstance() = entity
            override fun getFieldClass() = Int::class.java
            override fun getValue(entity: PaymentId<*, F>) = entity.getPaymentId()
            override fun hashCode() = toString().hashCode()
            override fun toString() = "$entityName.$fieldName"
            override fun setValue(entity: PaymentId<*, F>, value: F){
                entity.setPaymentId(value)
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
