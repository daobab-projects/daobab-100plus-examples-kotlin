package io.daobab.demo.dao.column

import io.daobab.model.Column
import io.daobab.model.EntityMap
import io.daobab.model.EntityRelationMap



interface Name<E : EntityMap, F> : EntityRelationMap<E> {

    fun getName(): F = getColumnParam("Name")
    @Suppress("UNCHECKED_CAST")
    fun setName(value: F): E {
		setColumnParam("Name", value)
		return this as E
	}
    /**
     * table:CATEGORY,type:VARCHAR,size:25,nullable:false
     * table:LANGUAGE,type:VARCHAR,size:20,nullable:false
     */
    fun colName() =
        object : Column<E, F, Name<*, F>> {
            override fun getColumnName() = "NAME"
            override fun getFieldName() = "Name"
            override fun getInstance() = entity
            override fun getFieldClass() = String::class.java
            override fun getValue(entity: Name<*, F>) = entity.getName()
            override fun hashCode() = toString().hashCode()
            override fun toString() = "$entityName.$fieldName"
            override fun setValue(entity: Name<*, F>, value: F){
                entity.setName(value)
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
