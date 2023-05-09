package io.daobab.demo.example.part_e

import io.daobab.demo.DemoApplication
import io.daobab.demo.base.ServiceBase
import io.daobab.demo.example.part_e.LinkingEntitiesByWhere
import io.daobab.model.FlatPlate
import org.springframework.boot.SpringApplication
import org.springframework.stereotype.Component

/**
 * ---------------------------------------------------------
 * Linking Entities by Where
 * ---------------------------------------------------------
 * - How to use Where for linking Entities (instead of joins)
 */
@Component
class LinkingEntitiesByWhere : ServiceBase<FlatPlate?>() {
    override fun call(): FlatPlate {
        val f = db.tabFilm
        val fc = db.tabFilmCategory
        val c = db.tabCategory
        return db.select(f.colTitle(), c.colName())
            .whereAnd {
                it
                    .equal(f.colFilmId(), fc.colFilmId())
                    .equal(fc.colCategoryId(), c.colCategoryId())
                    .equal(f.colID(), 15)
            }
            .findOneAsFlat()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(DemoApplication::class.java, LinkingEntitiesByWhere::class.java.name)
        }
    }
}
