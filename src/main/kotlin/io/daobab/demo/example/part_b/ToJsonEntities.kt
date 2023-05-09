package io.daobab.demo.example.part_b

import io.daobab.demo.DemoApplication
import io.daobab.demo.base.ServiceBase
import org.springframework.boot.SpringApplication
import org.springframework.stereotype.Component

/**
 * ---------------------------------------------------------
 * Entities To JSon
 * ---------------------------------------------------------
 * - How to use internal toJSON() method into Entities
 */
@Component
class ToJsonEntities : ServiceBase<Unit>() {
    override fun call() {
        val film= db
            .select(db.tabFilm)
            .whereLess(db.tabFilm.colID(), 10)
            .findMany()
        log.info(film.toJSON())
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(DemoApplication::class.java, ToJsonEntities::class.java.name)
        }
    }
}
