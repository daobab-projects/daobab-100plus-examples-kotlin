package io.daobab.demo.example.part_c

import io.daobab.demo.DemoApplication
import io.daobab.demo.base.ServiceBase
import io.daobab.demo.dao.table.Film
import org.springframework.boot.SpringApplication
import org.springframework.stereotype.Component

/**
 * ---------------------------------------------------------
 * Select Native One Cell
 * ---------------------------------------------------------
 */
@Component
class SelectNativeOneEntity : ServiceBase<Film>() {
    override fun call(): Film = db.nativeSelect("select * from film where film_id=15", db.tabFilm)
            .findOne()

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(DemoApplication::class.java, SelectNativeOneEntity::class.java.name)
        }
    }
}
