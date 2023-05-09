package io.daobab.demo.dao.table

import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.JsonInclude
import io.daobab.clone.EntityDuplicator
import io.daobab.model.Column
import io.daobab.model.TableColumn
import io.daobab.model.PrimaryKey
import io.daobab.demo.dao.column.PaymentId
import io.daobab.demo.dao.column.CustomerId
import io.daobab.demo.dao.column.StaffId
import io.daobab.demo.dao.column.RentalId
import io.daobab.demo.dao.column.Amount
import io.daobab.demo.dao.column.PaymentDate
import io.daobab.demo.dao.column.LastUpdate

import io.daobab.model.Table

import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonAutoDetect(fieldVisibility = ANY, getterVisibility = NONE, setterVisibility = NONE)
class Payment : Table(), 
	PaymentId<Payment, Int>,
	CustomerId<Payment, Int>,
	StaffId<Payment, Int>,
	RentalId<Payment, BigDecimal?>,
	Amount<Payment, BigDecimal>,
	PaymentDate<Payment, LocalDateTime>,
	LastUpdate<Payment, LocalDateTime>,

	PrimaryKey<Payment,Int,PaymentId<*, Int>>
	{

	override fun getEntityName() = "PAYMENT"

	override fun columns() = 
		listOf(
			TableColumn(colPaymentId()).primaryKey().size(16),
			TableColumn(colCustomerId()).size(16),
			TableColumn(colStaffId()).size(8),
			TableColumn(colRentalId()).size(32),
			TableColumn(colAmount()).size(5).scale(2),
			TableColumn(colPaymentDate()).size(26).scale(6),
			TableColumn(colLastUpdate()).size(26).scale(6)
		)

	override fun clone(): Payment  {
		return EntityDuplicator.cloneEntity(this)
	}

	override fun colID() = colPaymentId() 

	override fun hashCode() = Objects.hashCode(id)

    override fun equals(obj: Any?): Boolean {
        if (this === obj) return true
        if (obj == null) return false
        if (javaClass != obj.javaClass) return false
        val other: PrimaryKey<*, *, *> = obj as PrimaryKey<*, *, *>
        return id == other.id
    }



}
