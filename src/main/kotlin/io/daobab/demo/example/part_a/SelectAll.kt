package io.daobab.demo.example.part_a

import io.daobab.demo.DemoApplication
import io.daobab.demo.base.ServiceBase
import io.daobab.demo.dao.table.Actor
import io.daobab.target.buffer.single.Entities
import org.springframework.boot.SpringApplication
import org.springframework.stereotype.Component

/**
 * ---------------------------------------------------------
 * Get All Entities
 * ---------------------------------------------------------
 * - How to get all entities without any conditions
 * ---------------------------------------------------------
 * - Entities class is a List instance
 * - Entities may be used as a In-Memory Target
 */
@Component
class SelectAll : ServiceBase<Entities<Actor>>() {
    //Classic usage - no 'where' clause
    override fun call(): Entities<Actor> =
        db.select(db.tabActor).findMany()

    //Shortest usage with 'findAll' method
    fun scenario02(): Entities<Actor> {
        return db.findAll(db.tabActor)
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(DemoApplication::class.java, SelectAll::class.java.name)
        }
    }
}
