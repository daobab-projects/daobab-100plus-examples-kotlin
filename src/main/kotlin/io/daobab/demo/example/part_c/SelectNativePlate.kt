package io.daobab.demo.example.part_c

import io.daobab.demo.DemoApplication
import io.daobab.demo.base.ServiceBase
import io.daobab.model.FlatPlate
import org.springframework.boot.SpringApplication
import org.springframework.stereotype.Component

/**
 * ---------------------------------------------------------
 * Select Native Plate
 * ---------------------------------------------------------
 */
@Component
class SelectNativePlate : ServiceBase<FlatPlate>() {
    override fun call(): FlatPlate =
        db.nativeSelect(
            """
                select f.title,c.name from film f 
                join film_category fc on fc.film_id=f.film_id 
                join category c on c.category_id=fc.category_id 
                where f.film_id=15
                """,
            db.tabFilm.colTitle(), db.tabCategory.colName()
        ).findOneAsFlat()


    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(DemoApplication::class.java, SelectNativePlate::class.java.name)
        }
    }
}
