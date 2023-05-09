package io.daobab.demo.dao.column

import io.daobab.demo.dao.Lang
import io.daobab.model.Column
import io.daobab.model.EntityMap
import io.daobab.model.EntityRelationMap


interface NameLang<E : EntityMap> : EntityRelationMap<E> {


    /**
     * CATEGORY: VARCHAR
     * LANGUAGE: VARCHAR
     */
    fun getName(): Lang? {
		return getColumnParam("Name")
}
    fun setName(value: Lang?): E {
		setColumnParam("Name", value)
		return this as E
	}

    fun colName(): Column<E, Lang, NameLang<*>> {
        return object : Column<E, Lang, NameLang<*>> {
            override fun getColumnName(): String {
                return "NAME"
            }

            override fun getFieldName(): String {
                return "Name"
            }

            override fun getInstance(): E {
                return entity
            }

            override fun getFieldClass(): Class<Lang> {
                return Lang::class.java
            }

            override fun getValue(entity: NameLang<*>): Lang? {
                return entity.getName()
            }

            override fun setValue(entity: NameLang<*>, value: Lang?){
                entity.setName(value)
            }

            override fun hashCode(): Int {
                return toString().hashCode()
            }

            override fun toString(): String {
                return "$entityName.$fieldName"
            }

            override fun equals(obj: Any?): Boolean {
                if (this === obj)return true
                if (obj == null)return false
                if (javaClass != obj.javaClass)return false
                val other = obj as Column<*, *, *>
                return hashCode() == other.hashCode()
            }
        }
    }

}
