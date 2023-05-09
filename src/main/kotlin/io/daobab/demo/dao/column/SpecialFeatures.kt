package io.daobab.demo.dao.column

import io.daobab.model.Column
import io.daobab.model.EntityRelationMap
import io.daobab.model.EntityMap



interface SpecialFeatures<E : EntityMap, F> : EntityRelationMap<E> {

    fun getSpecialFeatures(): F = getColumnParam("SpecialFeatures")
    @Suppress("UNCHECKED_CAST")
    fun setSpecialFeatures(value: F): E {
		setColumnParam("SpecialFeatures", value)
		return this as E
	}
    /**
     * table:FILM,type:VARCHAR,size:54,nullable:true
     */
    fun colSpecialFeatures() =
        object : Column<E, F, SpecialFeatures<*, F>> {
            override fun getColumnName() = "SPECIAL_FEATURES"
            override fun getFieldName() = "SpecialFeatures"
            override fun getInstance() = entity
            override fun getFieldClass() = String::class.java
            override fun getValue(entity: SpecialFeatures<*, F>) = entity.getSpecialFeatures()
            override fun hashCode() = toString().hashCode()
            override fun toString() = "$entityName.$fieldName"
            override fun setValue(entity: SpecialFeatures<*, F>, value: F){
                entity.setSpecialFeatures(value)
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
