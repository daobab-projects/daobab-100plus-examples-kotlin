package io.daobab.demo.example.part_d

import io.daobab.demo.DemoApplication
import io.daobab.demo.base.ServiceBase
import io.daobab.demo.dao.table.Film
import io.daobab.target.buffer.single.Entities
import org.springframework.boot.SpringApplication
import org.springframework.stereotype.Component

/**
 * ---------------------------------------------------------
 * Remote Select Many Entities
 * ---------------------------------------------------------
 * - How to select many entities remotely
 */
@Component
class RemoteSelectManyEntities : ServiceBase<Entities<Film>>() {

    override fun call(): Entities<Film> =
        remote.select(db.tabFilm)
            .whereLess(db.tabFilm.colID(), 30)
            .findMany()

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(DemoApplication::class.java, RemoteSelectManyEntities::class.java.name)
        }
    }
}
