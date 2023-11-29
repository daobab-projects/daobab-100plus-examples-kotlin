package io.daobab.demo.example.part_c

import io.daobab.demo.DemoApplication
import io.daobab.demo.base.ServiceBase
import io.daobab.model.Entity
import io.daobab.statement.condition.SetFields
import org.springframework.boot.SpringApplication
import org.springframework.stereotype.Component
import java.time.LocalDateTime

/**
 * ---------------------------------------------------------
 * Insert Select
 * ---------------------------------------------------------
 */
@Component
class InsertDuplicatedKeyUpdate : ServiceBase<Unit>() {
    override fun call() {
        db.insert<Entity>(db.tabFilm) //inner select provides a compliant entity or compliant bunch of columns
            .select(
                db.select(db.tabFilm)
                    .whereLess(db.tabFilm.colFilmId(), 10)
            )
            .onDuplicateKeyUpdate(
                SetFields()
                    .setValue(db.tabFilm.colLastUpdate(), LocalDateTime.now())
                    .setValue(db.tabFilm.colDescription(), "done")
                    .setValue(db.tabFilm.colLength(), 20)
            )
            .execute(false)
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(DemoApplication::class.java, InsertDuplicatedKeyUpdate::class.java.name)
        }
    }
}
