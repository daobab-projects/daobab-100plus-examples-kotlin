package io.daobab.demo.example.part_a

import io.daobab.demo.DemoApplication
import io.daobab.demo.base.ExampleTag
import io.daobab.demo.base.ServiceBase
import io.daobab.demo.base.Tag
import io.daobab.demo.dao.table.Actor
import io.daobab.demo.dao.table.FilmActor
import io.daobab.demo.example.part_a.SelectOnePrimaryKeyEntity
import org.springframework.boot.SpringApplication
import org.springframework.stereotype.Component

/**
 * ---------------------------------------------------------
 * Get entity by PrimaryKey ID
 * ---------------------------------------------------------
 * - How to get an entity having PK value
 * - How to get an entity having FK related entity object
 * - How to get an entity through related entity method
 *
 * Daobab attach universal ID column to every Primary Key entity,
 * whatever Entity contains such column or not.
 */
@Component
class SelectOnePrimaryKeyEntity : ServiceBase<Actor>() {
    override fun call(): Actor {
        return scenario01()
    }

    @ExampleTag(Tag.SELECT) //Classic usage
    fun scenario01(): Actor =
        db.select(db.tabActor)
            .whereEqual(db.tabActor.colID(), 1)
            .findOne()

    //Using reference for shorter query
    fun scenario02(): Actor {
        val t = db.tabActor
        return db.select(t)
            .whereEqual(t.colID(), 1)
            .findOne()
    }

    //Quick select by pointing target,table and id value
    fun scenario03(): Actor = db.findOneByPk(db.tabActor, 1)

    //Using related Entity as ID carrier
    fun scenario04(): Actor {
        val relatedEntity = FilmActor()
        relatedEntity.setActorId(1)
        return db.select(db.tabActor)
            .whereEqual(db.tabActor.colID(), relatedEntity)
            .findOne()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(DemoApplication::class.java, SelectOnePrimaryKeyEntity::class.java.name)
        }
    }
}
