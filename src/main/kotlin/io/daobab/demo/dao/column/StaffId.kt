package io.daobab.demo.dao.column

import io.daobab.model.Column
import io.daobab.model.EntityRelationMap
import io.daobab.model.EntityMap



interface StaffId<E : EntityMap, F> : EntityRelationMap<E> {

    fun getStaffId(): F = getColumnParam("StaffId")
    @Suppress("UNCHECKED_CAST")
    fun setStaffId(value: F): E {
		setColumnParam("StaffId", value)
		return this as E
	}
    /**
     * table:PAYMENT,type:TINYINT,size:8,nullable:false
     * table:RENTAL,type:TINYINT,size:8,nullable:false
     * table:STAFF,type:TINYINT,size:8,nullable:false
     */
    fun colStaffId() =
        object : Column<E, F, StaffId<*, F>> {
            override fun getColumnName() = "STAFF_ID"
            override fun getFieldName() = "StaffId"
            override fun getInstance() = entity
            override fun getFieldClass() = Int::class.java
            override fun getValue(entity: StaffId<*, F>) = entity.getStaffId()
            override fun hashCode() = toString().hashCode()
            override fun toString() = "$entityName.$fieldName"
            override fun setValue(entity: StaffId<*, F>, value: F){
                entity.setStaffId(value)
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
