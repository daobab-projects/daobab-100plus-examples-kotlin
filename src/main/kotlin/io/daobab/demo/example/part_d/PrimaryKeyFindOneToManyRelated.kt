package io.daobab.demo.example.part_d

import io.daobab.demo.DemoApplication
import io.daobab.demo.base.ServiceBase
import io.daobab.demo.dao.table.FilmActor
import io.daobab.demo.example.part_d.PrimaryKeyFindOneToManyRelated
import org.springframework.boot.SpringApplication
import org.springframework.stereotype.Component

/**
 * ---------------------------------------------------------
 * PK method - find related items
 * ---------------------------------------------------------
 * - How to use PK method to find related entities. Relation OneToMany.
 */
@Component
class PrimaryKeyFindOneToManyRelated : ServiceBase<List<FilmActor>>() {
    override fun call(): List<FilmActor> {
        //Get some Entity from DB
        val film = db.findOneByPk(db.tabFilm, 1)
        //Find related
        return film.findRelatedMany(db, db.tabFilmActor)
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(DemoApplication::class.java, PrimaryKeyFindOneToManyRelated::class.java.name)
        }
    }
}
