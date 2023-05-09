package io.daobab.demo.example.part_e

import io.daobab.demo.DemoApplication
import io.daobab.demo.base.ServiceBase
import io.daobab.model.Entity
import io.daobab.target.database.query.DataBaseQueryBase
import org.springframework.boot.SpringApplication
import org.springframework.stereotype.Component

/**
 * ---------------------------------------------------------
 * QueryModificator
 * ---------------------------------------------------------
 * Daobab Query is an objet and may be modified
 */
@Component
class QueryModification : ServiceBase<Unit>() {
    override fun call() {
        val query1 = db.select(db.tabFilm).orderAscBy(db.tabFilm.colFilmId())
        val query2 = db.select(db.tabFilm.colTitle()).orderAscBy(db.tabFilm.colFilmId())
        val query3 = db.select(db.tabFilm.colTitle(), db.tabFilm.colDescription()).orderAscBy(db.tabFilm.colFilmId())
        val result1 = applyLimit(query1).findMany().size
        val result2 = applyLimit(query2).findMany().size
        val result3 = applyLimit(query3).findMany().size
        log.info("result 1: {}", result1)
        log.info("result 2: {}", result2)
        log.info("result 3: {}", result3)
    }

    fun <Q : DataBaseQueryBase<E, Q>, E : Entity> applyLimit(query: Q): Q {
        return query.limitBy(5)
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(DemoApplication::class.java, QueryModification::class.java.name)
        }
    }
}
