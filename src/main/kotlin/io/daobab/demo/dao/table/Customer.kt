package io.daobab.demo.dao.table

import io.daobab.demo.dao.column.CustomerId
import io.daobab.demo.dao.column.StoreId
import io.daobab.demo.dao.column.FirstName
import io.daobab.demo.dao.column.LastName
import io.daobab.demo.dao.column.Email
import io.daobab.demo.dao.column.AddressId
import io.daobab.demo.dao.column.Active
import io.daobab.demo.dao.column.CreateDate
import io.daobab.demo.dao.column.LastUpdate

import io.daobab.creation.DaobabCache
import io.daobab.model.*
import java.time.LocalDateTime
import java.util.*

class Customer : Table<Customer>, 
	CustomerId<Customer, Int>,
	StoreId<Customer, Int>,
	FirstName<Customer, String>,
	LastName<Customer, String>,
	Email<Customer, String?>,
	AddressId<Customer, Int>,
	Active<Customer, Boolean>,
	CreateDate<Customer, LocalDateTime>,
	LastUpdate<Customer, LocalDateTime>,

	PrimaryKey<Customer,Int,CustomerId<*, Int>>
	{

	constructor (): super()

	constructor (parameters: Map<String?, Any?>?): super(parameters)

	override fun columns(): List<TableColumn> = 
		DaobabCache.getTableColumns(this) {
			Arrays.asList(
			TableColumn(colCustomerId()).primaryKey().size(16),
			TableColumn(colStoreId()).size(8),
			TableColumn(colFirstName()).size(45),
			TableColumn(colLastName()).size(45),
			TableColumn(colEmail()).size(50),
			TableColumn(colAddressId()).size(16),
			TableColumn(colActive()).size(1),
			TableColumn(colCreateDate()).size(26).scale(6),
			TableColumn(colLastUpdate()).size(26).scale(6)

	)
}
	
override fun colID(): Column<Customer, Int, CustomerId<*, Int>> = colCustomerId() as Column< Customer, Int, CustomerId<*, Int>>

	override fun hashCode() = Objects.hashCode(id)

	override fun equals(obj: Any?): Boolean {
		if (this === obj) return true
		if (obj == null) return false
		if (javaClass != obj.javaClass) return false
		val other: PrimaryKey<*, *, *> = obj as PrimaryKey<*, *, *>
		return id == other.id
	}

}
