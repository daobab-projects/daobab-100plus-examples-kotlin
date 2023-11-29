package io.daobab.demo.example.part_c

import io.daobab.demo.DemoApplication
import io.daobab.demo.base.ServiceBase
import io.daobab.target.database.meta.MetaDataTables
import org.springframework.boot.SpringApplication
import org.springframework.stereotype.Component

/**
 * ---------------------------------------------------------
 * Statistic
 * ---------------------------------------------------------
 */
@Component
class Statistic : ServiceBase<Unit>(), MetaDataTables {
    override fun call() {
        db.enableStatisticCollecting(true)
        db.statisticCollector.bufferSize = 100
        db.statisticCollector.ignoreSuccessful(true)
        db.statisticCollector.ignoreExecutionTimeBelow(5000)

        db.select(db.tabFilm)
            .whereLess(db.tabFilm.colFilmId(), 10)
            .findMany()
        db.select(db.tabFilm)
            .whereLess(db.tabFilm.colFilmId(), 10)
            .findMany()
        try {
            Thread.sleep(1000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        val rs = db.statistics.findMany()
        log.info(rs.size.toString())
        rs.onEach { log.info(it.toJson()) }
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(DemoApplication::class.java, Statistic::class.java.name)
        }
    }
}
