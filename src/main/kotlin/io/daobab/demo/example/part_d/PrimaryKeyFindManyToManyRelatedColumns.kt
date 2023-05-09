package io.daobab.demo.example.part_d

import io.daobab.demo.DemoApplication
import io.daobab.demo.base.ServiceBase
import io.daobab.demo.dao.table.Actor
import org.springframework.boot.SpringApplication
import org.springframework.stereotype.Component

/**
 * ---------------------------------------------------------
 * PK method - find selected columns from related Entities with many to many cross table
 * ---------------------------------------------------------
 * - How to use PK method to get selected columns of related entities, through cross table. Relation ManyToMany.
 */
@Component
class PrimaryKeyFindManyToManyRelatedColumns : ServiceBase<List<Actor>>() {
    override fun call(): List<Actor> {
        //Get some Entity from DB
        val film = db.findOneByPk(db.tabFilm, 1)
        //Find related
        return film.findRelatedManyByCross(db, db.tabFilmActor, db.tabActor.colFirstName(), db.tabActor.colLastName())
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(DemoApplication::class.java, PrimaryKeyFindManyToManyRelatedColumns::class.java.name)
        }
    }
}
