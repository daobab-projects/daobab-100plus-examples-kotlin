package io.daobab.demo.example.part_c

import io.daobab.demo.DemoApplication
import io.daobab.demo.base.ServiceBase
import io.daobab.demo.example.part_c.ConcurrencySafeUpdate
import org.springframework.boot.SpringApplication
import org.springframework.stereotype.Component
import java.time.LocalDateTime

/**
 * ---------------------------------------------------------
 * Concurrency Safe Update
 * ---------------------------------------------------------
 * - How to use concurrency safe update.
 * In this example each row is modified into separated transaction.
 * Records are not locked by big transaction.
 */
@Component
class ConcurrencySafeUpdate : ServiceBase<Unit>() {
    override fun call() {
        val f = db.tabFilm

        //This may create deadlocks
//     Update.to(db,f).set(SetFields
//             .setColumn(f.colLastUpdate(),toCurrentTimestamp()))
//             .where(f.colFilmId(),LT,20)
//             .execute();
        db.select(f)
            .whereLess(f.colFilmId(), 20)
            .findMany()
            .forEach { it.setLastUpdate(LocalDateTime.now()).update(db, f.colLastUpdate()) }
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(DemoApplication::class.java, ConcurrencySafeUpdate::class.java.name)
        }
    }
}
