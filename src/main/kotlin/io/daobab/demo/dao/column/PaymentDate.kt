package io.daobab.demo.dao.column

import io.daobab.creation.DaobabCache
import io.daobab.model.*;
import java.time.LocalDateTime
interface PaymentDate<E : Entity, F> : RelatedTo<E>, MapHandler<E> {

	fun getPaymentDate(): F = readParam("PaymentDate")

	fun setPaymentDate(value: F): E {
		return storeParam("PaymentDate", value)
	}

    /**
     * table:PAYMENT, type:TIMESTAMP, size:26, nullable:false
     */
	fun colPaymentDate(): Column<E, F, out RelatedTo<E>> =
		DaobabCache.getColumn("PaymentDate", "PAYMENT_DATE", this as Table<*>, LocalDateTime::class.java) as  Column<E, F, out RelatedTo<E>> 
	
}
