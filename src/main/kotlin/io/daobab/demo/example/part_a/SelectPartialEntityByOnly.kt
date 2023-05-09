package io.daobab.demo.example.part_a

import io.daobab.demo.DemoApplication
import io.daobab.demo.base.ServiceBase
import io.daobab.demo.dao.table.Staff
import io.daobab.target.buffer.single.Entities
import org.springframework.boot.SpringApplication
import org.springframework.stereotype.Component

/**
 * ---------------------------------------------------------
 * Get Entities but only certain columns - by selecting only columns
 * ---------------------------------------------------------
 * Warning: PK cannot be removed if the entity has it.
 */
@Component
class SelectPartialEntityByOnly : ServiceBase<Entities<Staff>>() {
    override fun call(): Entities<Staff> =
        db.select(db.tabStaff)
            .only(db.tabStaff.colPicture(), db.tabStaff.colPassword())
            .findMany()


    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(DemoApplication::class.java, SelectPartialEntityByOnly::class.java.name)
        }
    }
}
