package io.daobab.demo.dao.table

import io.daobab.demo.dao.column.StaffId
import io.daobab.demo.dao.column.FirstName
import io.daobab.demo.dao.column.LastName
import io.daobab.demo.dao.column.AddressId
import io.daobab.demo.dao.column.Picture
import io.daobab.demo.dao.column.Email
import io.daobab.demo.dao.column.StoreId
import io.daobab.demo.dao.column.Active
import io.daobab.demo.dao.column.Username
import io.daobab.demo.dao.column.Password
import io.daobab.demo.dao.column.LastUpdate

import io.daobab.creation.DaobabCache
import io.daobab.model.*
import java.time.LocalDateTime
import java.util.*

class Staff : Table<Staff>, 
	StaffId<Staff, Int>,
	FirstName<Staff, String>,
	LastName<Staff, String>,
	AddressId<Staff, Int>,
	Picture<Staff, ByteArray?>,
	Email<Staff, String?>,
	StoreId<Staff, Int>,
	Active<Staff, Boolean>,
	Username<Staff, String>,
	Password<Staff, String?>,
	LastUpdate<Staff, LocalDateTime>,

	PrimaryKey<Staff,Int,StaffId<*, Int>>
	{

	constructor (): super()

	constructor (parameters: Map<String?, Any?>?): super(parameters)

	override fun columns(): List<TableColumn> = 
		DaobabCache.getTableColumns(this) {
			Arrays.asList(
			TableColumn(colStaffId()).primaryKey().size(8),
			TableColumn(colFirstName()).size(45),
			TableColumn(colLastName()).size(45),
			TableColumn(colAddressId()).size(16),
			TableColumn(colPicture()).size(1000000000),
			TableColumn(colEmail()).size(50),
			TableColumn(colStoreId()).size(8),
			TableColumn(colActive()).size(1),
			TableColumn(colUsername()).size(16),
			TableColumn(colPassword()).size(40),
			TableColumn(colLastUpdate()).size(26).scale(6)

	)
}
	
override fun colID(): Column<Staff, Int, StaffId<*, Int>> = colStaffId() as Column< Staff, Int, StaffId<*, Int>>

	override fun hashCode() = Objects.hashCode(id)

	override fun equals(obj: Any?): Boolean {
		if (this === obj) return true
		if (obj == null) return false
		if (javaClass != obj.javaClass) return false
		val other: PrimaryKey<*, *, *> = obj as PrimaryKey<*, *, *>
		return id == other.id
	}

}
