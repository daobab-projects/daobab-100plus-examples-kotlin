package io.daobab.demo.example.part_c

import io.daobab.demo.DemoApplication
import io.daobab.demo.base.ServiceBase
import org.springframework.boot.SpringApplication
import org.springframework.stereotype.Component

/**
 * ---------------------------------------------------------
 * SQL Operator Minus
 * ---------------------------------------------------------
 * - Example haw to use 'minus'
 */
@Component
class Minus : ServiceBase<List<Int>>() {

    override fun call(): List<Int> = db.select(db.tabFilmActor.colFilmId())
            .whereLess(db.tabFilmActor.colFilmId(), 110)
            .minus(
                db.select(db.tabFilmCategory.colFilmId())
                    .whereGreater(db.tabFilmCategory.colFilmId(), 10)
            )
            .findMany()


    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(DemoApplication::class.java, Minus::class.java.name)
        }
    }
}
