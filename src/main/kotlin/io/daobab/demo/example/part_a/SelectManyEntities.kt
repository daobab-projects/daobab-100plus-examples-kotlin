package io.daobab.demo.example.part_a

import io.daobab.demo.DemoApplication
import io.daobab.demo.base.ServiceBase
import io.daobab.demo.dao.table.Actor
import io.daobab.demo.example.part_a.SelectManyEntities
import io.daobab.target.buffer.single.Entities
import org.springframework.boot.SpringApplication
import org.springframework.stereotype.Component

/**
 * ---------------------------------------------------------
 * - How to get many entities
 * ---------------------------------------------------------
 * - Entities class is a List instance
 * - Entities object is a target as well
 */
@Component
class SelectManyEntities : ServiceBase<Entities<Actor>>() {
    override fun call(): Entities<Actor> = db.select(db.tabActor).whereLess(db.tabActor.colActorId(), 10).findMany()

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(DemoApplication::class.java, SelectManyEntities::class.java.name)
        }
    }
}
