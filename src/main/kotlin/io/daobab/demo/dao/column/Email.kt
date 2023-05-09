package io.daobab.demo.dao.column

import io.daobab.model.Column
import io.daobab.model.EntityRelationMap
import io.daobab.model.EntityMap



interface Email<E : EntityMap, F> : EntityRelationMap<E> {

    fun getEmail(): F = getColumnParam("Email")
    @Suppress("UNCHECKED_CAST")
    fun setEmail(value: F): E {
		setColumnParam("Email", value)
		return this as E
	}
    /**
     * table:CUSTOMER,type:VARCHAR,size:50,nullable:true
     * table:STAFF,type:VARCHAR,size:50,nullable:true
     */
    fun colEmail() =
        object : Column<E, F, Email<*, F>> {
            override fun getColumnName() = "EMAIL"
            override fun getFieldName() = "Email"
            override fun getInstance() = entity
            override fun getFieldClass() = String::class.java
            override fun getValue(entity: Email<*, F>) = entity.getEmail()
            override fun hashCode() = toString().hashCode()
            override fun toString() = "$entityName.$fieldName"
            override fun setValue(entity: Email<*, F>, value: F){
                entity.setEmail(value)
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
