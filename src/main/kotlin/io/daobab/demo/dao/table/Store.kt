package io.daobab.demo.dao.table

import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE
import com.fasterxml.jackson.annotation.JsonInclude
import io.daobab.clone.EntityDuplicator
import io.daobab.demo.dao.column.AddressId
import io.daobab.demo.dao.column.LastUpdate
import io.daobab.demo.dao.column.ManagerStaffId
import io.daobab.demo.dao.column.StoreId
import io.daobab.model.PrimaryKey
import io.daobab.model.Table
import io.daobab.model.TableColumn
import java.time.LocalDateTime
import java.util.*

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonAutoDetect(fieldVisibility = ANY, getterVisibility = NONE, setterVisibility = NONE)
class Store : Table(), 
	StoreId<Store, Int>,
	ManagerStaffId<Store, Int>,
	AddressId<Store, Int>,
	LastUpdate<Store, LocalDateTime>,

	PrimaryKey<Store,Int,StoreId<*, Int>>
	{

	override fun getEntityName() = "STORE"

	override fun columns() = 
		listOf(
			TableColumn(colStoreId()).primaryKey().size(8),
			TableColumn(colManagerStaffId()).size(8),
			TableColumn(colAddressId()).size(16),
			TableColumn(colLastUpdate()).size(26).scale(6)
		)

	override fun clone(): Store  {
		return EntityDuplicator.cloneEntity(this)
	}

	override fun colID() = colStoreId() 

	override fun hashCode() = Objects.hashCode(id)

    override fun equals(obj: Any?): Boolean {
        if (this === obj) return true
        if (obj == null) return false
        if (javaClass != obj.javaClass) return false
        val other: PrimaryKey<*, *, *> = obj as PrimaryKey<*, *, *>
        return id == other.id
    }



}
