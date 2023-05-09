package io.daobab.demo.example.part_d

import io.daobab.demo.DemoApplication
import io.daobab.demo.base.ServiceBase
import io.daobab.demo.dao.table.FilmCategory
import org.springframework.boot.SpringApplication
import org.springframework.stereotype.Component

/**
 * ---------------------------------------------------------
 * PK method - find related item
 * ---------------------------------------------------------
 * - How to use PK method to find related entity. Relation OneToOne.
 */
@Component
class PrimaryKeyFindOneToOneRelated : ServiceBase<FilmCategory>() {
    override fun call(): FilmCategory {
        //Get some Entity from DB
        val film = db.findOneByPk(db.tabFilm, 1) //Find related
        return film.findRelatedOne(db, db.tabFilmCategory)
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(DemoApplication::class.java, PrimaryKeyFindOneToOneRelated::class.java.name)
        }
    }
}
