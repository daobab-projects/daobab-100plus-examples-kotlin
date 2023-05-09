package io.daobab.demo.dao.column

import io.daobab.model.Column
import io.daobab.model.EntityMap
import io.daobab.model.EntityRelationMap


interface FilmIdTest<E : EntityMap, F> : EntityRelationMap<E> {


    /**
     * FILM: SMALLINT
     * FILM_ACTOR: SMALLINT
     * FILM_CATEGORY: SMALLINT
     * FILM_TEXT: SMALLINT
     * INVENTORY: SMALLINT
     */
    fun getFilmId(): F {
        return getColumnParam("FilmId")
    }

    fun setFilmId(value: F): E {
        setColumnParam("FilmId", value)
        return this as E
    }

    fun colFilmId(): Column<E, F, FilmIdTest<*, F>> {
        return object : Column<E, F, FilmIdTest<*, F>> {
            override fun getColumnName(): String {
                return "FILM_ID"
            }

            override fun getFieldName(): String {
                return "FilmId"
            }

            override fun getInstance(): E {
                return entity
            }

            override fun getFieldClass(): Class<Int> {
                return Int::class.java
            }

            override fun getValue(entity: FilmIdTest<*, F>): F {
                return entity.getFilmId()
            }

            override fun setValue(entity: FilmIdTest<*, F>, value: F) {
                entity.setFilmId(value)
            }

            override fun hashCode(): Int {
                return toString().hashCode()
            }

            override fun toString(): String {
                return "$entityName.$fieldName"
            }

            override fun equals(obj: Any?): Boolean {
                if (this === obj) return true
                if (obj == null) return false
                if (javaClass != obj.javaClass) return false
                val other = obj as Column<*, *, *>
                return hashCode() == other.hashCode()
            }
        }
    }

}
