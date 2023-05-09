package io.daobab.demo.example.part_c

import io.daobab.demo.DemoApplication
import io.daobab.demo.base.ServiceBase
import io.daobab.demo.dao.table.Film
import org.springframework.boot.SpringApplication
import org.springframework.stereotype.Component
import java.time.ZoneId

/**
 * ---------------------------------------------------------
 * DateTime Conversion
 * ---------------------------------------------------------
 * - How to use time parser
 */
@Component
class DateTimeConversions : ServiceBase<List<Film>>() {
    override fun call(): List<Film> {
        val t = db.tabFilm
        return db.select(t)
            .whereLess(t.colID(), 5)
            .map { it.setLastUpdate(it.getLastUpdate().atZone(ZoneId.of("US/Alaska ")).toLocalDateTime()) }
            .findMany()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(DemoApplication::class.java, DateTimeConversions::class.java.name)
        }
    }
}
