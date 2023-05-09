package io.daobab.demo.example.part_e

import io.daobab.demo.DemoApplication
import io.daobab.demo.base.ServiceBase
import io.daobab.demo.dao.table.enhanced.EnglishFilm
import io.daobab.target.buffer.single.Entities
import org.springframework.boot.SpringApplication
import org.springframework.stereotype.Component

/**
 * ---------------------------------------------------------
 * Enhanced Entity
 * ---------------------------------------------------------
 * - How to enhance Entity (see into EnglishFilm entity)
 */
@Component
class EnhancedEntity : ServiceBase<Entities<EnglishFilm>>() {
    override fun call(): Entities<EnglishFilm> {
        val t = EnglishFilm()
        return db.select(t)
            .whereNotNull(t.colTitle())
            .limitBy(5)
            .findMany()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(DemoApplication::class.java, EnhancedEntity::class.java.name)
        }
    }
}
