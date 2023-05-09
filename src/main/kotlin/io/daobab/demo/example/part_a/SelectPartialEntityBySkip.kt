package io.daobab.demo.example.part_a

import io.daobab.demo.DemoApplication
import io.daobab.demo.base.ServiceBase
import io.daobab.demo.dao.table.Staff
import org.springframework.boot.SpringApplication
import org.springframework.stereotype.Component

/**
 * ---------------------------------------------------------
 * Get Entities but without certain columns - by skipping some columns
 * ---------------------------------------------------------
 * Warning: PK cannot be skipped if the entity has it.
 */
@Component
class SelectPartialEntityBySkip : ServiceBase<List<Staff>>() {
    override fun call(): List<Staff> =
        db.select(db.tabStaff)
            .skip(db.tabStaff.colPicture(), db.tabStaff.colPassword())
            .findMany()

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(DemoApplication::class.java, SelectPartialEntityBySkip::class.java.name)
        }
    }
}
