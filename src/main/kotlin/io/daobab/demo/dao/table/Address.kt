package io.daobab.demo.dao.table

import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE
import com.fasterxml.jackson.annotation.JsonInclude
import io.daobab.clone.EntityDuplicator
import io.daobab.demo.dao.column.*
import io.daobab.model.PrimaryKey
import io.daobab.model.Table
import io.daobab.model.TableColumn
import java.time.LocalDateTime
import java.util.*

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonAutoDetect(fieldVisibility = ANY, getterVisibility = NONE, setterVisibility = NONE)
class Address : Table(), 
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

	override fun getEntityName() = "ADDRESS"

	override fun columns() = 
		listOf(
			TableColumn(colAddressId()).primaryKey().size(16),
			TableColumn(colAddress()).size(50),
			TableColumn(colAddress2()).size(50),
			TableColumn(colDistrict()).size(20),
			TableColumn(colCityId()).size(16),
			TableColumn(colPostalCode()).size(10),
			TableColumn(colPhone()).size(20),
			TableColumn(colLastUpdate()).size(26).scale(6)
		)

	override fun clone(): Address  {
		return EntityDuplicator.cloneEntity(this)
	}

	override fun colID() = colAddressId() 

	override fun hashCode() = Objects.hashCode(id)

    override fun equals(obj: Any?): Boolean {
        if (this === obj) return true
        if (obj == null) return false
        if (javaClass != obj.javaClass) return false
        val other: PrimaryKey<*, *, *> = obj as PrimaryKey<*, *, *>
        return id == other.id
    }



}
