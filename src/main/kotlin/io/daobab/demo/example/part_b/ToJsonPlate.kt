package io.daobab.demo.example.part_b

import io.daobab.demo.DemoApplication
import io.daobab.demo.base.ServiceBase
import io.daobab.result.FlatPlates
import org.springframework.boot.SpringApplication
import org.springframework.stereotype.Component

/**
 * ---------------------------------------------------------
 * Plate To JSon
 * ---------------------------------------------------------
 * - How to use internal toJson() method into Plate
 */
@Component
class ToJsonPlate : ServiceBase<FlatPlates>() {
    override fun call(): FlatPlates {
        val film =
            db.select(db.tabFilm.colTitle(), db.tabFilm.colDescription()).whereLess(db.tabFilm.colID(), 10).findManyAsFlat()
        log.info(film.toJson())
        return film
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(DemoApplication::class.java, ToJsonPlate::class.java.name)
        }
    }
}
