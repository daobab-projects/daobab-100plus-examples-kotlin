package io.daobab.demo.dao.column

import io.daobab.model.Column
import io.daobab.model.EntityMap
import io.daobab.model.EntityRelationMap



interface Username<E : EntityMap, F> : EntityRelationMap<E> {

    fun getUsername(): F = getColumnParam("Username")
    @Suppress("UNCHECKED_CAST")
    fun setUsername(value: F): E {
		setColumnParam("Username", value)
		return this as E
	}
    /**
     * table:STAFF,type:VARCHAR,size:16,nullable:false
     */
    fun colUsername() =
        object : Column<E, F, Username<*, F>> {
            override fun getColumnName() = "USERNAME"
            override fun getFieldName() = "Username"
            override fun getInstance() = entity
            override fun getFieldClass() = String::class.java
            override fun getValue(entity: Username<*, F>) = entity.getUsername()
            override fun hashCode() = toString().hashCode()
            override fun toString() = "$entityName.$fieldName"
            override fun setValue(entity: Username<*, F>, value: F){
                entity.setUsername(value)
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
