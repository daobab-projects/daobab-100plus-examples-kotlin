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
class Customer : Table(), 
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

	override fun getEntityName() = "CUSTOMER"

	override fun columns() = 
		listOf(
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

	override fun clone(): Customer  {
		return EntityDuplicator.cloneEntity(this)
	}

	override fun colID() = colCustomerId() 

	override fun hashCode() = Objects.hashCode(id)

    override fun equals(obj: Any?): Boolean {
        if (this === obj) return true
        if (obj == null) return false
        if (javaClass != obj.javaClass) return false
        val other: PrimaryKey<*, *, *> = obj as PrimaryKey<*, *, *>
        return id == other.id
    }



}
