package io.daobab.demo.dao.column

import io.daobab.model.Column
import io.daobab.model.EntityRelationMap
import io.daobab.model.EntityMap



interface CategoryId<E : EntityMap, F> : EntityRelationMap<E> {

    fun getCategoryId(): F = getColumnParam("CategoryId")
    @Suppress("UNCHECKED_CAST")
    fun setCategoryId(value: F): E {
		setColumnParam("CategoryId", value)
		return this as E
	}
    /**
     * table:CATEGORY,type:TINYINT,size:8,nullable:false
     * table:FILM_CATEGORY,type:TINYINT,size:8,nullable:false
     */
    fun colCategoryId() =
        object : Column<E, F, CategoryId<*, F>> {
            override fun getColumnName() = "CATEGORY_ID"
            override fun getFieldName() = "CategoryId"
            override fun getInstance() = entity
            override fun getFieldClass() = Int::class.java
            override fun getValue(entity: CategoryId<*, F>) = entity.getCategoryId()
            override fun hashCode() = toString().hashCode()
            override fun toString() = "$entityName.$fieldName"
            override fun setValue(entity: CategoryId<*, F>, value: F){
                entity.setCategoryId(value)
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
