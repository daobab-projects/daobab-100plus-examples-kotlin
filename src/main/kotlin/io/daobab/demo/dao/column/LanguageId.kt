package io.daobab.demo.dao.column

import io.daobab.model.Column
import io.daobab.model.EntityRelationMap
import io.daobab.model.EntityMap



interface LanguageId<E : EntityMap, F> : EntityRelationMap<E> {

    fun getLanguageId(): F = getColumnParam("LanguageId")
    @Suppress("UNCHECKED_CAST")
    fun setLanguageId(value: F): E {
		setColumnParam("LanguageId", value)
		return this as E
	}
    /**
     * table:FILM,type:TINYINT,size:8,nullable:false
     * table:LANGUAGE,type:TINYINT,size:8,nullable:false
     */
    fun colLanguageId() =
        object : Column<E, F, LanguageId<*, F>> {
            override fun getColumnName() = "LANGUAGE_ID"
            override fun getFieldName() = "LanguageId"
            override fun getInstance() = entity
            override fun getFieldClass() = Int::class.java
            override fun getValue(entity: LanguageId<*, F>) = entity.getLanguageId()
            override fun hashCode() = toString().hashCode()
            override fun toString() = "$entityName.$fieldName"
            override fun setValue(entity: LanguageId<*, F>, value: F){
                entity.setLanguageId(value)
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
