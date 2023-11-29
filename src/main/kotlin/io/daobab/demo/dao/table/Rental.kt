package io.daobab.demo.dao.table

import io.daobab.demo.dao.column.RentalId
import io.daobab.demo.dao.column.RentalDate
import io.daobab.demo.dao.column.InventoryId
import io.daobab.demo.dao.column.CustomerId
import io.daobab.demo.dao.column.ReturnDate
import io.daobab.demo.dao.column.StaffId
import io.daobab.demo.dao.column.LastUpdate

import io.daobab.creation.DaobabCache
import io.daobab.model.*
import java.time.LocalDateTime
import java.math.BigDecimal
import java.util.*

class Rental : Table<Rental>, 
	RentalId<Rental, BigDecimal>,
	RentalDate<Rental, LocalDateTime>,
	InventoryId<Rental, BigDecimal>,
	CustomerId<Rental, Int>,
	ReturnDate<Rental, LocalDateTime?>,
	StaffId<Rental, Int>,
	LastUpdate<Rental, LocalDateTime>,

	PrimaryKey<Rental,BigDecimal,RentalId<*, BigDecimal>>
	{

	constructor (): super()

	constructor (parameters: Map<String?, Any?>?): super(parameters)

	override fun columns(): List<TableColumn> = 
		DaobabCache.getTableColumns(this) {
			Arrays.asList(
			TableColumn(colRentalId()).primaryKey().size(32),
			TableColumn(colRentalDate()).size(26).scale(6),
			TableColumn(colInventoryId()).size(32),
			TableColumn(colCustomerId()).size(16),
			TableColumn(colReturnDate()).size(26).scale(6),
			TableColumn(colStaffId()).size(8),
			TableColumn(colLastUpdate()).size(26).scale(6)

	)
}
	
override fun colID(): Column<Rental, BigDecimal, RentalId<*, BigDecimal>> = colRentalId() as Column< Rental, BigDecimal, RentalId<*, BigDecimal>>

	override fun hashCode() = Objects.hashCode(id)

	override fun equals(obj: Any?): Boolean {
		if (this === obj) return true
		if (obj == null) return false
		if (javaClass != obj.javaClass) return false
		val other: PrimaryKey<*, *, *> = obj as PrimaryKey<*, *, *>
		return id == other.id
	}

}
