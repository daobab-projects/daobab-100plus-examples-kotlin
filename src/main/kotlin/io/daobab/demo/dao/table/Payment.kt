package io.daobab.demo.dao.table

import io.daobab.demo.dao.column.PaymentId
import io.daobab.demo.dao.column.CustomerId
import io.daobab.demo.dao.column.StaffId
import io.daobab.demo.dao.column.RentalId
import io.daobab.demo.dao.column.Amount
import io.daobab.demo.dao.column.PaymentDate
import io.daobab.demo.dao.column.LastUpdate

import io.daobab.creation.DaobabCache
import io.daobab.model.*
import java.time.LocalDateTime
import java.math.BigDecimal
import java.util.*

class Payment : Table<Payment>, 
	PaymentId<Payment, Int>,
	CustomerId<Payment, Int>,
	StaffId<Payment, Int>,
	RentalId<Payment, BigDecimal?>,
	Amount<Payment, BigDecimal>,
	PaymentDate<Payment, LocalDateTime>,
	LastUpdate<Payment, LocalDateTime>,

	PrimaryKey<Payment,Int,PaymentId<*, Int>>
	{

	constructor (): super()

	constructor (parameters: Map<String?, Any?>?): super(parameters)

	override fun columns(): List<TableColumn> = 
		DaobabCache.getTableColumns(this) {
			Arrays.asList(
			TableColumn(colPaymentId()).primaryKey().size(16),
			TableColumn(colCustomerId()).size(16),
			TableColumn(colStaffId()).size(8),
			TableColumn(colRentalId()).size(32),
			TableColumn(colAmount()).size(5).scale(2),
			TableColumn(colPaymentDate()).size(26).scale(6),
			TableColumn(colLastUpdate()).size(26).scale(6)

	)
}
	
override fun colID(): Column<Payment, Int, PaymentId<*, Int>> = colPaymentId() as Column< Payment, Int, PaymentId<*, Int>>

	override fun hashCode() = Objects.hashCode(id)

	override fun equals(obj: Any?): Boolean {
		if (this === obj) return true
		if (obj == null) return false
		if (javaClass != obj.javaClass) return false
		val other: PrimaryKey<*, *, *> = obj as PrimaryKey<*, *, *>
		return id == other.id
	}

}
