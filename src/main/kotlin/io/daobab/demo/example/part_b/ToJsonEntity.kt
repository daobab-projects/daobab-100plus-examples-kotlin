package io.daobab.demo.example.part_b

import io.daobab.demo.base.ServiceBase
import io.daobab.demo.example.part_a.Count
import org.springframework.boot.SpringApplication
import org.springframework.stereotype.Component

/**
 * ---------------------------------------------------------
 * Entity to JSON
 * ---------------------------------------------------------
 * - How to use internal toJSON() method into Entity
 */
@Component
class ToJsonEntity : ServiceBase<Unit>() {
    override fun call() {
        val film= db.findOneByPk(db.tabFilm, 1)
        log.info(film.toJSON())
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(ToJsonEntity::class.java, Count::class.java.name)
        }
    }
}
