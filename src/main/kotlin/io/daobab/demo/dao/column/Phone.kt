package io.daobab.demo.dao.column

import io.daobab.model.Column
import io.daobab.model.EntityRelationMap
import io.daobab.model.EntityMap



interface Phone<E : EntityMap, F> : EntityRelationMap<E> {

    fun getPhone(): F = getColumnParam("Phone")
    @Suppress("UNCHECKED_CAST")
    fun setPhone(value: F): E {
		setColumnParam("Phone", value)
		return this as E
	}
    /**
     * table:ADDRESS,type:VARCHAR,size:20,nullable:false
     */
    fun colPhone() =
        object : Column<E, F, Phone<*, F>> {
            override fun getColumnName() = "PHONE"
            override fun getFieldName() = "Phone"
            override fun getInstance() = entity
            override fun getFieldClass() = String::class.java
            override fun getValue(entity: Phone<*, F>) = entity.getPhone()
            override fun hashCode() = toString().hashCode()
            override fun toString() = "$entityName.$fieldName"
            override fun setValue(entity: Phone<*, F>, value: F){
                entity.setPhone(value)
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
