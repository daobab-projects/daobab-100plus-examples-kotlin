package io.daobab.demo.dao.column

import io.daobab.model.Column
import io.daobab.model.EntityRelationMap
import io.daobab.model.EntityMap

import java.time.LocalDateTime

interface CreateDate<E : EntityMap, F> : EntityRelationMap<E> {

    fun getCreateDate(): F = getColumnParam("CreateDate")
    @Suppress("UNCHECKED_CAST")
    fun setCreateDate(value: F): E {
		setColumnParam("CreateDate", value)
		return this as E
	}
    /**
     * table:CUSTOMER,type:TIMESTAMP,size:26,nullable:false
     */
    fun colCreateDate() =
        object : Column<E, F, CreateDate<*, F>> {
            override fun getColumnName() = "CREATE_DATE"
            override fun getFieldName() = "CreateDate"
            override fun getInstance() = entity
            override fun getFieldClass() = LocalDateTime::class.java
            override fun getValue(entity: CreateDate<*, F>) = entity.getCreateDate()
            override fun hashCode() = toString().hashCode()
            override fun toString() = "$entityName.$fieldName"
            override fun setValue(entity: CreateDate<*, F>, value: F){
                entity.setCreateDate(value)
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
