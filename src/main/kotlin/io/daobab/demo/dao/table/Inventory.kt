package io.daobab.demo.dao.table

import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.JsonInclude
import io.daobab.clone.EntityDuplicator
import io.daobab.model.Column
import io.daobab.model.TableColumn
import io.daobab.model.PrimaryKey
import io.daobab.demo.dao.column.InventoryId
import io.daobab.demo.dao.column.FilmId
import io.daobab.demo.dao.column.StoreId
import io.daobab.demo.dao.column.LastUpdate

import io.daobab.model.Table

import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonAutoDetect(fieldVisibility = ANY, getterVisibility = NONE, setterVisibility = NONE)
class Inventory : Table(), 
	InventoryId<Inventory, BigDecimal>,
	FilmId<Inventory, Int>,
	StoreId<Inventory, Int>,
	LastUpdate<Inventory, LocalDateTime>,

	PrimaryKey<Inventory,BigDecimal,InventoryId<*, BigDecimal>>
	{

	override fun getEntityName() = "INVENTORY"

	override fun columns() = 
		listOf(
			TableColumn(colInventoryId()).primaryKey().size(32),
			TableColumn(colFilmId()).size(16),
			TableColumn(colStoreId()).size(8),
			TableColumn(colLastUpdate()).size(26).scale(6)
		)

	override fun clone(): Inventory  {
		return EntityDuplicator.cloneEntity(this)
	}

	override fun colID() = colInventoryId() 

	override fun hashCode() = Objects.hashCode(id)

    override fun equals(obj: Any?): Boolean {
        if (this === obj) return true
        if (obj == null) return false
        if (javaClass != obj.javaClass) return false
        val other: PrimaryKey<*, *, *> = obj as PrimaryKey<*, *, *>
        return id == other.id
    }



}
