package io.daobab.demo.example.part_d

import io.daobab.demo.DemoApplication
import io.daobab.demo.base.ServiceBase
import io.daobab.demo.dao.table.FilmActor
import io.daobab.demo.example.part_d.PrimaryKeyFindOneToOneRelatedColumns
import org.springframework.boot.SpringApplication
import org.springframework.stereotype.Component

/**
 * ---------------------------------------------------------
 * PK method - find just selected columns from related Entity
 * ---------------------------------------------------------
 * - How to use PK method to get just selected columns from related entity. Relation OneToOne.
 */
@Component
class PrimaryKeyFindOneToOneRelatedColumns : ServiceBase<FilmActor>() {
    override fun call(): FilmActor {
        //Get some Entity from DB
        val film = db.findOneByPk(db.tabFilm, 1)
        //Find related
        val t = db.tabFilmActor
        return film.findRelatedOne(db, t, t.colFilmId(), t.colLastUpdate())
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(DemoApplication::class.java, PrimaryKeyFindOneToOneRelatedColumns::class.java.name)
        }
    }
}
