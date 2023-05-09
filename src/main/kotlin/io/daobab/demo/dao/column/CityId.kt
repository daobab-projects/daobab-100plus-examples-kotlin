package io.daobab.demo.dao.column

import io.daobab.model.Column
import io.daobab.model.EntityMap
import io.daobab.model.EntityRelationMap



interface CityId<E : EntityMap, F> : EntityRelationMap<E> {

    fun getCityId(): F = getColumnParam("CityId")
    @Suppress("UNCHECKED_CAST")
    fun setCityId(value: F): E {
		setColumnParam("CityId", value)
		return this as E
	}
    /**
     * table:ADDRESS,type:SMALLINT,size:16,nullable:false
     * table:CITY,type:SMALLINT,size:16,nullable:false
     */
    fun colCityId() =
        object : Column<E, F, CityId<*, F>> {
            override fun getColumnName() = "CITY_ID"
            override fun getFieldName() = "CityId"
            override fun getInstance() = entity
            override fun getFieldClass() = Int::class.java
            override fun getValue(entity: CityId<*, F>) = entity.getCityId()
            override fun hashCode() = toString().hashCode()
            override fun toString() = "$entityName.$fieldName"
            override fun setValue(entity: CityId<*, F>, value: F){
                entity.setCityId(value)
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
