package io.daobab.demo.example.part_c

import io.daobab.demo.DemoApplication
import io.daobab.demo.base.ServiceBase
import io.daobab.demo.example.part_c.UnionAll
import org.springframework.boot.SpringApplication
import org.springframework.stereotype.Component

/**
 * ---------------------------------------------------------
 * SQL Operator Union
 * ---------------------------------------------------------
 * - Example haw to use 'union'
 */
@Component
class UnionAll : ServiceBase<List<Int>>() {

    override fun call(): List<Int> = db.select(db.tabFilmActor.colFilmId())
            .whereLess(db.tabFilmActor.colFilmId(), 10)
            .union(
                db.select(db.tabFilmCategory.colFilmId())
                    .whereGreater(db.tabFilmCategory.colFilmId(), 990)
            )
            .findMany()

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(DemoApplication::class.java, UnionAll::class.java.name)
        }
    }
}
