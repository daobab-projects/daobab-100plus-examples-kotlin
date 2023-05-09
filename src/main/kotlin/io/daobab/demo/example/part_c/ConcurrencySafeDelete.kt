package io.daobab.demo.example.part_c

import io.daobab.demo.DemoApplication
import io.daobab.demo.base.ServiceBase
import io.daobab.demo.example.part_c.ConcurrencySafeDelete
import org.springframework.boot.SpringApplication
import org.springframework.stereotype.Component

/**
 * ---------------------------------------------------------
 * Concurrency Safe Delete
 * ---------------------------------------------------------
 * - How to use concurrency safe delete.
 * In this example each row is deleted into separated transaction.
 * Records are not locked by big transaction.
 */
@Component
class ConcurrencySafeDelete : ServiceBase<Unit>() {
    override fun call(){
        val f = db.tabFilm

        //This may create deadlocks
//     Delete.from(db,f)
//             .whereGreater(f.colFilmId(),2000)
//             .execute();
        db.select(f)
            .whereGreater(f.colFilmId(), 2000)
            .findMany()
            .forEach { it.delete(db) }
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(DemoApplication::class.java, ConcurrencySafeDelete::class.java.name)
        }
    }
}
