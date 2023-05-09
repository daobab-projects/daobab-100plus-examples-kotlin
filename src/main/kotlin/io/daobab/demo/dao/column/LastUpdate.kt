package io.daobab.demo.dao.column

import io.daobab.model.Column
import io.daobab.model.EntityRelationMap
import io.daobab.model.EntityMap

import java.time.LocalDateTime

interface LastUpdate<E : EntityMap, F> : EntityRelationMap<E> {

    fun getLastUpdate(): F = getColumnParam("LastUpdate")
    @Suppress("UNCHECKED_CAST")
    fun setLastUpdate(value: F): E {
		setColumnParam("LastUpdate", value)
		return this as E
	}
    /**
     * table:ACTOR,type:TIMESTAMP,size:26,nullable:false
     * table:ADDRESS,type:TIMESTAMP,size:26,nullable:false
     * table:CATEGORY,type:TIMESTAMP,size:26,nullable:false
     * table:CITY,type:TIMESTAMP,size:26,nullable:false
     * table:COUNTRY,type:TIMESTAMP,size:26,nullable:false
     * table:CUSTOMER,type:TIMESTAMP,size:26,nullable:false
     * table:FILM,type:TIMESTAMP,size:26,nullable:false
     * table:FILM_ACTOR,type:TIMESTAMP,size:26,nullable:false
     * table:FILM_CATEGORY,type:TIMESTAMP,size:26,nullable:false
     * table:INVENTORY,type:TIMESTAMP,size:26,nullable:false
     * table:LANGUAGE,type:TIMESTAMP,size:26,nullable:false
     * table:PAYMENT,type:TIMESTAMP,size:26,nullable:false
     * table:RENTAL,type:TIMESTAMP,size:26,nullable:false
     * table:STAFF,type:TIMESTAMP,size:26,nullable:false
     * table:STORE,type:TIMESTAMP,size:26,nullable:false
     */
    fun colLastUpdate() =
        object : Column<E, F, LastUpdate<*, F>> {
            override fun getColumnName() = "LAST_UPDATE"
            override fun getFieldName() = "LastUpdate"
            override fun getInstance() = entity
            override fun getFieldClass() = LocalDateTime::class.java
            override fun getValue(entity: LastUpdate<*, F>) = entity.getLastUpdate()
            override fun hashCode() = toString().hashCode()
            override fun toString() = "$entityName.$fieldName"
            override fun setValue(entity: LastUpdate<*, F>, value: F){
                entity.setLastUpdate(value)
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
