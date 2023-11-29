package io.daobab.demo.dao.table

import io.daobab.demo.dao.column.AddressId
import io.daobab.demo.dao.column.Address2
import io.daobab.demo.dao.column.District
import io.daobab.demo.dao.column.CityId
import io.daobab.demo.dao.column.PostalCode
import io.daobab.demo.dao.column.Phone
import io.daobab.demo.dao.column.LastUpdate

import io.daobab.creation.DaobabCache
import io.daobab.model.*
import java.time.LocalDateTime
import java.util.*

class Address : Table<Address>, 
	AddressId<Address, Int>,
	io.daobab.demo.dao.column.Address<Address, String>,
	Address2<Address, String?>,
	District<Address, String>,
	CityId<Address, Int>,
	PostalCode<Address, String?>,
	Phone<Address, String>,
	LastUpdate<Address, LocalDateTime>,

	PrimaryKey<Address,Int,AddressId<*, Int>>
	{

	constructor (): super()

	constructor (parameters: Map<String?, Any?>?): super(parameters)

	override fun columns(): List<TableColumn> = 
		DaobabCache.getTableColumns(this) {
			Arrays.asList(
			TableColumn(colAddressId()).primaryKey().size(16),
			TableColumn(colAddress()).size(50),
			TableColumn(colAddress2()).size(50),
			TableColumn(colDistrict()).size(20),
			TableColumn(colCityId()).size(16),
			TableColumn(colPostalCode()).size(10),
			TableColumn(colPhone()).size(20),
			TableColumn(colLastUpdate()).size(26).scale(6)

	)
}
	
override fun colID(): Column<Address, Int, AddressId<*, Int>> = colAddressId() as Column< Address, Int, AddressId<*, Int>>

	override fun hashCode() = Objects.hashCode(id)

	override fun equals(obj: Any?): Boolean {
		if (this === obj) return true
		if (obj == null) return false
		if (javaClass != obj.javaClass) return false
		val other: PrimaryKey<*, *, *> = obj as PrimaryKey<*, *, *>
		return id == other.id
	}

}
