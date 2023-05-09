package io.daobab.demo.dao.column

import io.daobab.model.Column
import io.daobab.model.EntityRelationMap
import io.daobab.model.EntityMap



interface Password<E : EntityMap, F> : EntityRelationMap<E> {

    fun getPassword(): F = getColumnParam("Password")
    @Suppress("UNCHECKED_CAST")
    fun setPassword(value: F): E {
		setColumnParam("Password", value)
		return this as E
	}
    /**
     * table:STAFF,type:VARCHAR,size:40,nullable:true
     */
    fun colPassword() =
        object : Column<E, F, Password<*, F>> {
            override fun getColumnName() = "PASSWORD"
            override fun getFieldName() = "Password"
            override fun getInstance() = entity
            override fun getFieldClass() = String::class.java
            override fun getValue(entity: Password<*, F>) = entity.getPassword()
            override fun hashCode() = toString().hashCode()
            override fun toString() = "$entityName.$fieldName"
            override fun setValue(entity: Password<*, F>, value: F){
                entity.setPassword(value)
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
