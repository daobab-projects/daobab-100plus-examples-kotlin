package io.daobab.demo.dao.column

import io.daobab.model.Column
import io.daobab.model.EntityRelationMap
import io.daobab.model.EntityMap



interface Picture<E : EntityMap, F> : EntityRelationMap<E> {

    fun getPicture(): F = getColumnParam("Picture")
    @Suppress("UNCHECKED_CAST")
    fun setPicture(value: F): E {
		setColumnParam("Picture", value)
		return this as E
	}
    /**
     * table:STAFF,type:VARBINARY,size:1000000000,nullable:true
     */
    fun colPicture() =
        object : Column<E, F, Picture<*, F>> {
            override fun getColumnName() = "PICTURE"
            override fun getFieldName() = "Picture"
            override fun getInstance() = entity
            override fun getFieldClass() = ByteArray::class.java
            override fun getValue(entity: Picture<*, F>) = entity.getPicture()
            override fun hashCode() = toString().hashCode()
            override fun toString() = "$entityName.$fieldName"
            override fun setValue(entity: Picture<*, F>, value: F){
                entity.setPicture(value)
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
