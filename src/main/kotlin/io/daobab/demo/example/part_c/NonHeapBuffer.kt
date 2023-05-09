package io.daobab.demo.example.part_c

import io.daobab.demo.DemoApplication
import io.daobab.demo.base.ServiceBase
import io.daobab.target.database.meta.MetaDataTables
import org.springframework.boot.SpringApplication
import org.springframework.stereotype.Component

/**
 * ---------------------------------------------------------
 * NonHeapBuffer
 * ---------------------------------------------------------
 */
@Component
class NonHeapBuffer : ServiceBase<Unit>(), MetaDataTables {
    override fun call() {
        val nonHeap = db.select(db.tabFilm).toNonHeap()
        nonHeap.select(db.tabFilm).whereLess(db.tabFilm.colFilmId(), 4).findMany().forEach { log.info(it.toJSON()) }
        db.select(db.tabFilm).whereLess(db.tabFilm.colFilmId(), 4).findMany().forEach { log.info(it.toJSON()) }
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(DemoApplication::class.java, NonHeapBuffer::class.java.name)
        }
    }
}
