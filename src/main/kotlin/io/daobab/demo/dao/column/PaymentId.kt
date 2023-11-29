package io.daobab.demo.dao.column

import io.daobab.creation.DaobabCache
import io.daobab.model.*;

interface PaymentId<E : Entity, F> : RelatedTo<E>, MapHandler<E> {

	fun getPaymentId(): F = readParam("PaymentId")

	fun setPaymentId(value: F): E {
		return storeParam("PaymentId", value)
	}

    /**
     * table:PAYMENT, type:SMALLINT, size:16, nullable:false
     */
	fun colPaymentId(): Column<E, F, out RelatedTo<E>> =
		DaobabCache.getColumn("PaymentId", "PAYMENT_ID", this as Table<*>, Int::class.java) as  Column<E, F, out RelatedTo<E>> 
	
}
