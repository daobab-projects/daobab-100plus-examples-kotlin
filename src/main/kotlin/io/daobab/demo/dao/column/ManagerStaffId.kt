package io.daobab.demo.dao.column

import io.daobab.model.Column
import io.daobab.model.EntityRelationMap
import io.daobab.model.EntityMap



interface ManagerStaffId<E : EntityMap, F> : EntityRelationMap<E> {

    fun getManagerStaffId(): F = getColumnParam("ManagerStaffId")
    @Suppress("UNCHECKED_CAST")
    fun setManagerStaffId(value: F): E {
		setColumnParam("ManagerStaffId", value)
		return this as E
	}
    /**
     * table:STORE,type:TINYINT,size:8,nullable:false
     */
    fun colManagerStaffId() =
        object : Column<E, F, ManagerStaffId<*, F>> {
            override fun getColumnName() = "MANAGER_STAFF_ID"
            override fun getFieldName() = "ManagerStaffId"
            override fun getInstance() = entity
            override fun getFieldClass() = Int::class.java
            override fun getValue(entity: ManagerStaffId<*, F>) = entity.getManagerStaffId()
            override fun hashCode() = toString().hashCode()
            override fun toString() = "$entityName.$fieldName"
            override fun setValue(entity: ManagerStaffId<*, F>, value: F){
                entity.setManagerStaffId(value)
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
