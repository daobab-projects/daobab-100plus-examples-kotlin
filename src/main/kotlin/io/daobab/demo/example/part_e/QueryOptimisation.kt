package io.daobab.demo.example.part_e

import io.daobab.demo.DemoApplication
import io.daobab.demo.base.ServiceBase
import org.springframework.boot.SpringApplication
import org.springframework.stereotype.Component

/**
 * ---------------------------------------------------------
 * Query Optimisation
 * ---------------------------------------------------------
 * - Daobab reorders where clause to make the query execution faster
 */
@Component
class QueryOptimisation : ServiceBase<Unit>() {
    override fun call() {
        db.select(db.tabFilm)
            .whereAnd {
                it
                    .notNull(db.tabFilm.colLength())
                    .like(db.tabFilm.colDescription(), "%a%")
                    .equal(db.tabFilm.colID(), 10)
            }.findOne()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(DemoApplication::class.java, QueryOptimisation::class.java.name)
        }
    }
}
