package io.daobab.demo.dao.table

import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.JsonInclude
import io.daobab.clone.EntityDuplicator
import io.daobab.model.Column
import io.daobab.model.TableColumn
import io.daobab.model.PrimaryKey
import io.daobab.demo.dao.column.RentalId
import io.daobab.demo.dao.column.RentalDate
import io.daobab.demo.dao.column.InventoryId
import io.daobab.demo.dao.column.CustomerId
import io.daobab.demo.dao.column.ReturnDate
import io.daobab.demo.dao.column.StaffId
import io.daobab.demo.dao.column.LastUpdate

import io.daobab.model.Table

import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonAutoDetect(fieldVisibility = ANY, getterVisibility = NONE, setterVisibility = NONE)
class Rental : Table(), 
	RentalId<Rental, BigDecimal>,
	RentalDate<Rental, LocalDateTime>,
	InventoryId<Rental, BigDecimal>,
	CustomerId<Rental, Int>,
	ReturnDate<Rental, LocalDateTime?>,
	StaffId<Rental, Int>,
	LastUpdate<Rental, LocalDateTime>,

	PrimaryKey<Rental,BigDecimal,RentalId<*, BigDecimal>>
	{

	override fun getEntityName() = "RENTAL"

	override fun columns() = 
		listOf(
			TableColumn(colRentalId()).primaryKey().size(32),
			TableColumn(colRentalDate()).size(26).scale(6),
			TableColumn(colInventoryId()).size(32),
			TableColumn(colCustomerId()).size(16),
			TableColumn(colReturnDate()).size(26).scale(6),
			TableColumn(colStaffId()).size(8),
			TableColumn(colLastUpdate()).size(26).scale(6)
		)

	override fun clone(): Rental  {
		return EntityDuplicator.cloneEntity(this)
	}

	override fun colID() = colRentalId() 

	override fun hashCode() = Objects.hashCode(id)

    override fun equals(obj: Any?): Boolean {
        if (this === obj) return true
        if (obj == null) return false
        if (javaClass != obj.javaClass) return false
        val other: PrimaryKey<*, *, *> = obj as PrimaryKey<*, *, *>
        return id == other.id
    }



}
