package io.daobab.demo.example.part_c

import io.daobab.demo.DemoApplication
import io.daobab.demo.base.ServiceBase
import org.springframework.boot.SpringApplication
import org.springframework.stereotype.Component

/**
 * ---------------------------------------------------------
 * InMemory Select
 * ---------------------------------------------------------
 * - How to query over InMemory Entities
 */
@Component
class InMemorySelect : ServiceBase<List<String>>() {
    override fun call(): List<String> {
        val t = db.tabFilm

        //Get some content... here everything from table
        val allFilms = db.findAll(t)

        //execute SQL over this content - query only for titles of movies longer than...
        return allFilms.select(t.colTitle())
            .whereGreater(t.colLength(), 150)
            .limitBy(15)
            .findMany()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(DemoApplication::class.java, InMemorySelect::class.java.name)
        }
    }
}
