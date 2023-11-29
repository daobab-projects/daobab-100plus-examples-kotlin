package io.daobab.demo.dao.table

import io.daobab.demo.dao.column.InventoryId
import io.daobab.demo.dao.column.FilmId
import io.daobab.demo.dao.column.StoreId
import io.daobab.demo.dao.column.LastUpdate

import io.daobab.creation.DaobabCache
import io.daobab.model.*
import java.time.LocalDateTime
import java.math.BigDecimal
import java.util.*

class Inventory : Table<Inventory>, 
	InventoryId<Inventory, BigDecimal>,
	FilmId<Inventory, Int>,
	StoreId<Inventory, Int>,
	LastUpdate<Inventory, LocalDateTime>,

	PrimaryKey<Inventory,BigDecimal,InventoryId<*, BigDecimal>>
	{

	constructor (): super()

	constructor (parameters: Map<String?, Any?>?): super(parameters)

	override fun columns(): List<TableColumn> = 
		DaobabCache.getTableColumns(this) {
			Arrays.asList(
			TableColumn(colInventoryId()).primaryKey().size(32),
			TableColumn(colFilmId()).size(16),
			TableColumn(colStoreId()).size(8),
			TableColumn(colLastUpdate()).size(26).scale(6)

	)
}
	
override fun colID(): Column<Inventory, BigDecimal, InventoryId<*, BigDecimal>> = colInventoryId() as Column< Inventory, BigDecimal, InventoryId<*, BigDecimal>>

	override fun hashCode() = Objects.hashCode(id)

	override fun equals(obj: Any?): Boolean {
		if (this === obj) return true
		if (obj == null) return false
		if (javaClass != obj.javaClass) return false
		val other: PrimaryKey<*, *, *> = obj as PrimaryKey<*, *, *>
		return id == other.id
	}

}
