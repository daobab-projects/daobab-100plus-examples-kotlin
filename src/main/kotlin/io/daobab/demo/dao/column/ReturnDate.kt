package io.daobab.demo.dao.column

import io.daobab.model.Column
import io.daobab.model.EntityRelationMap
import io.daobab.model.EntityMap

import java.time.LocalDateTime

interface ReturnDate<E : EntityMap, F> : EntityRelationMap<E> {

    fun getReturnDate(): F = getColumnParam("ReturnDate")
    @Suppress("UNCHECKED_CAST")
    fun setReturnDate(value: F): E {
		setColumnParam("ReturnDate", value)
		return this as E
	}
    /**
     * table:RENTAL,type:TIMESTAMP,size:26,nullable:true
     */
    fun colReturnDate() =
        object : Column<E, F, ReturnDate<*, F>> {
            override fun getColumnName() = "RETURN_DATE"
            override fun getFieldName() = "ReturnDate"
            override fun getInstance() = entity
            override fun getFieldClass() = LocalDateTime::class.java
            override fun getValue(entity: ReturnDate<*, F>) = entity.getReturnDate()
            override fun hashCode() = toString().hashCode()
            override fun toString() = "$entityName.$fieldName"
            override fun setValue(entity: ReturnDate<*, F>, value: F){
                entity.setReturnDate(value)
            }
            override fun equals(other: Any?): Boolean {
                if (this === other) return true
                if (other == null) return false
                if (javaClass != other.javaClass) return false
                val otherColumn = other as Column<*, *, *>
                return hashCode() == otherColumn.hashCode()
            }
        }
    }
