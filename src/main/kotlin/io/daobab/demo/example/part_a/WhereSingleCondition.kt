package io.daobab.demo.example.part_a

import io.daobab.demo.DemoApplication
import io.daobab.demo.base.ServiceBase
import io.daobab.demo.dao.table.Customer
import io.daobab.demo.example.part_a.WhereSingleCondition
import org.springframework.boot.SpringApplication
import org.springframework.stereotype.Component

/**
 * ---------------------------------------------------------
 * - How to use where with a single condition
 * ---------------------------------------------------------
 */
@Component
class WhereSingleCondition : ServiceBase<Customer>() {
    override fun call(): Customer =
        db.select(db.tabCustomer)
            .whereEqual(db.tabCustomer.colEmail(), "BARBARA.JONES@sakilacustomer.org")
            .findOne()

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(DemoApplication::class.java, WhereSingleCondition::class.java.name)
        }
    }
}
