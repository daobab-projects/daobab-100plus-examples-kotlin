package io.daobab.demo.example.part_e

import io.daobab.demo.DemoApplication
import io.daobab.demo.base.ServiceBase
import io.daobab.demo.dao.table.FilmText
import org.springframework.boot.SpringApplication
import org.springframework.stereotype.Component

/**
 * ---------------------------------------------------------
 * SQL Injection Protection
 * ---------------------------------------------------------
 * - Daobab protection against SQLInjection
 */
@Component
class SqlInjectionProtection : ServiceBase<Unit>() {
    override fun call() {
        FilmText()
            .setFilmId(1)
            .setDescription(" ');  Drop table xxx; Select * from film where (film.title like '%a")
            .update(db, db.tabFilmText.colDescription())
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(DemoApplication::class.java, SqlInjectionProtection::class.java.name)
        }
    }
}
