package io.daobab.demo.example.part_d

import io.daobab.demo.DemoApplication
import io.daobab.demo.base.ServiceBase
import io.daobab.demo.dao.table.FilmActor
import org.springframework.boot.SpringApplication
import org.springframework.stereotype.Component

/**
 * ---------------------------------------------------------
 * PK method - find just selected columns from related Entities
 * ---------------------------------------------------------
 * - How to use PK method to get just selected columns from related entities. Relation OneToMany.
 */
@Component
class PrimaryKeyFindOneToManyRelatedColumns : ServiceBase<List<FilmActor>>() {
    override fun call(): List<FilmActor> {
        //Get some Entity from DB
        val film = db.findOneByPk(db.tabFilm, 1)
        //Find related
        val t = db.tabFilmActor
        return film.findRelatedMany(db, t, t.colFilmId(), t.colLastUpdate())
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(DemoApplication::class.java, PrimaryKeyFindOneToManyRelatedColumns::class.java.name)
        }
    }
}
