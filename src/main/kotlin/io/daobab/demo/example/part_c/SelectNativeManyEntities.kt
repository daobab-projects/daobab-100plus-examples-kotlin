package io.daobab.demo.example.part_c

import io.daobab.demo.DemoApplication
import io.daobab.demo.base.ServiceBase
import io.daobab.demo.dao.table.Film
import io.daobab.demo.example.part_c.SelectNativeManyEntities
import io.daobab.target.buffer.single.Entities
import org.springframework.boot.SpringApplication
import org.springframework.stereotype.Component

/**
 * ---------------------------------------------------------
 * Select Native Many Entities
 * ---------------------------------------------------------
 */
@Component
class SelectNativeManyEntities : ServiceBase<Entities<Film>>() {
    override fun call(): Entities<Film> =
        db.nativeSelect("select * from film where film_id<15", db.tabFilm)
            .findMany()

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(DemoApplication::class.java, SelectNativeManyEntities::class.java.name)
        }
    }
}
