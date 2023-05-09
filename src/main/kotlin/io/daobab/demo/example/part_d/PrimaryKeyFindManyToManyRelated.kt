package io.daobab.demo.example.part_d

import io.daobab.demo.DemoApplication
import io.daobab.demo.base.ServiceBase
import io.daobab.demo.dao.table.Actor
import io.daobab.demo.example.part_d.PrimaryKeyFindManyToManyRelated
import org.springframework.boot.SpringApplication
import org.springframework.stereotype.Component

/**
 * ---------------------------------------------------------
 * PK method - find related Entities with many to many cross table
 * ---------------------------------------------------------
 * - How to use PK method to find selected related entity, through cross table. Relation ManyToMany.
 */
@Component
class PrimaryKeyFindManyToManyRelated : ServiceBase<List<Actor>>() {
    override fun call(): List<Actor> {
        //Get some Entity from DB
        val film = db.findOneByPk(db.tabFilm, 1)
        //Find related
        return film.findRelatedManyByCross(db, db.tabFilmActor, db.tabActor.colActorId())
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(DemoApplication::class.java, PrimaryKeyFindManyToManyRelated::class.java.name)
        }
    }
}
