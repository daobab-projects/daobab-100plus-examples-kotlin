package io.daobab.demo.dao.table

import io.daobab.demo.dao.column.StoreId
import io.daobab.demo.dao.column.ManagerStaffId
import io.daobab.demo.dao.column.AddressId
import io.daobab.demo.dao.column.LastUpdate

import io.daobab.creation.DaobabCache
import io.daobab.model.*
import java.time.LocalDateTime
import java.util.*

class Store : Table<Store>, 
	StoreId<Store, Int>,
	ManagerStaffId<Store, Int>,
	AddressId<Store, Int>,
	LastUpdate<Store, LocalDateTime>,

	PrimaryKey<Store,Int,StoreId<*, Int>>
	{

	constructor (): super()

	constructor (parameters: Map<String?, Any?>?): super(parameters)

	override fun columns(): List<TableColumn> = 
		DaobabCache.getTableColumns(this) {
			Arrays.asList(
			TableColumn(colStoreId()).primaryKey().size(8),
			TableColumn(colManagerStaffId()).size(8),
			TableColumn(colAddressId()).size(16),
			TableColumn(colLastUpdate()).size(26).scale(6)

	)
}
	
override fun colID(): Column<Store, Int, StoreId<*, Int>> = colStoreId() as Column< Store, Int, StoreId<*, Int>>

	override fun hashCode() = Objects.hashCode(id)

	override fun equals(obj: Any?): Boolean {
		if (this === obj) return true
		if (obj == null) return false
		if (javaClass != obj.javaClass) return false
		val other: PrimaryKey<*, *, *> = obj as PrimaryKey<*, *, *>
		return id == other.id
	}

}
