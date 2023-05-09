package io.daobab.demo.example.part_c

import io.daobab.demo.DemoApplication
import io.daobab.demo.base.InMemoryFilm
import io.daobab.demo.base.ServiceBase
import io.daobab.demo.dao.table.Film
import io.daobab.parser.ParserGeneral
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import org.springframework.stereotype.Component

/**
 * ---------------------------------------------------------
 * - How to use AboveMultiEntity Target
 * ---------------------------------------------------------
 */
@Component
class AboveMultiEntity : ServiceBase<List<Film>>(), ParserGeneral {
    @Autowired
    lateinit var inMemoryFilm: InMemoryFilm

    override fun call(): List<Film> {
        return inMemoryFilm.select(db.tabFilm)
            .whereAnd {
                it.less(db.tabFilm.colFilmId(), 100)
                    .equal(db.tabFilm.colSpecialFeatures(), "Deleted Scenes")
                    .greater(db.tabFilm.colReplacementCost(), toBigDecimal(20))
            }
            .orderDescBy(db.tabFilm.colDescription())
            .findMany()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(DemoApplication::class.java, AboveMultiEntity::class.java.name)
        }
    }
}
