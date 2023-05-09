package io.daobab.demo.dao.column

import io.daobab.model.Column
import io.daobab.model.EntityRelationMap
import io.daobab.model.EntityMap



interface OriginalLanguageId<E : EntityMap, F> : EntityRelationMap<E> {

    fun getOriginalLanguageId(): F = getColumnParam("OriginalLanguageId")
    @Suppress("UNCHECKED_CAST")
    fun setOriginalLanguageId(value: F): E {
		setColumnParam("OriginalLanguageId", value)
		return this as E
	}
    /**
     * table:FILM,type:TINYINT,size:8,nullable:true
     */
    fun colOriginalLanguageId() =
        object : Column<E, F, OriginalLanguageId<*, F>> {
            override fun getColumnName() = "ORIGINAL_LANGUAGE_ID"
            override fun getFieldName() = "OriginalLanguageId"
            override fun getInstance() = entity
            override fun getFieldClass() = Int::class.java
            override fun getValue(entity: OriginalLanguageId<*, F>) = entity.getOriginalLanguageId()
            override fun hashCode() = toString().hashCode()
            override fun toString() = "$entityName.$fieldName"
            override fun setValue(entity: OriginalLanguageId<*, F>, value: F){
                entity.setOriginalLanguageId(value)
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
