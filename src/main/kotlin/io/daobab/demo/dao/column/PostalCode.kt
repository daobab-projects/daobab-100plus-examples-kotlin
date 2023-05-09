package io.daobab.demo.dao.column

import io.daobab.model.Column
import io.daobab.model.EntityRelationMap
import io.daobab.model.EntityMap



interface PostalCode<E : EntityMap, F> : EntityRelationMap<E> {

    fun getPostalCode(): F = getColumnParam("PostalCode")
    @Suppress("UNCHECKED_CAST")
    fun setPostalCode(value: F): E {
		setColumnParam("PostalCode", value)
		return this as E
	}
    /**
     * table:ADDRESS,type:VARCHAR,size:10,nullable:true
     */
    fun colPostalCode() =
        object : Column<E, F, PostalCode<*, F>> {
            override fun getColumnName() = "POSTAL_CODE"
            override fun getFieldName() = "PostalCode"
            override fun getInstance() = entity
            override fun getFieldClass() = String::class.java
            override fun getValue(entity: PostalCode<*, F>) = entity.getPostalCode()
            override fun hashCode() = toString().hashCode()
            override fun toString() = "$entityName.$fieldName"
            override fun setValue(entity: PostalCode<*, F>, value: F){
                entity.setPostalCode(value)
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
