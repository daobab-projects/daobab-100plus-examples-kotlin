package io.daobab.demo.dao.column

import io.daobab.model.Column
import io.daobab.model.EntityRelationMap
import io.daobab.model.EntityMap



interface CountryId<E : EntityMap, F> : EntityRelationMap<E> {

    fun getCountryId(): F = getColumnParam("CountryId")
    @Suppress("UNCHECKED_CAST")
    fun setCountryId(value: F): E {
		setColumnParam("CountryId", value)
		return this as E
	}
    /**
     * table:CITY,type:SMALLINT,size:16,nullable:false
     * table:COUNTRY,type:SMALLINT,size:16,nullable:false
     */
    fun colCountryId() =
        object : Column<E, F, CountryId<*, F>> {
            override fun getColumnName() = "COUNTRY_ID"
            override fun getFieldName() = "CountryId"
            override fun getInstance() = entity
            override fun getFieldClass() = Int::class.java
            override fun getValue(entity: CountryId<*, F>) = entity.getCountryId()
            override fun hashCode() = toString().hashCode()
            override fun toString() = "$entityName.$fieldName"
            override fun setValue(entity: CountryId<*, F>, value: F){
                entity.setCountryId(value)
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
