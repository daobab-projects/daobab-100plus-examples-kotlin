package io.daobab.demo.example.part_c

import io.daobab.demo.DemoApplication
import io.daobab.demo.base.ServiceBase
import io.daobab.demo.example.part_c.InsertSelect
import io.daobab.model.Entity
import org.springframework.boot.SpringApplication
import org.springframework.stereotype.Component

/**
 * ---------------------------------------------------------
 * Insert Select
 * ---------------------------------------------------------
 * - Example demonstrates, how to insert table rows based on compliant select.
 * Good practice to move data to archive table for example.
 * Insert and select should point on the same database.
 */
@Component
class InsertSelect : ServiceBase<Unit>() {
    override fun call() {
        db.insert<Entity>(db.tabFilm) //inner select provides a compliant entity or compliant bunch of columns
            .select(db.select(db.tabFilm).whereLess(db.tabFilm.colFilmId(), 10))
            .execute(false)
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(DemoApplication::class.java, InsertSelect::class.java.name)
        }
    }
}
