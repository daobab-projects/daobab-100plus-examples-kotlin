package io.daobab.demo.example.part_c

import io.daobab.demo.DemoApplication
import io.daobab.demo.base.ServiceBase
import io.daobab.demo.dao.table.Customer
import org.springframework.boot.SpringApplication
import org.springframework.stereotype.Component

/**
 * ---------------------------------------------------------
 * Limit results with Offset
 * ---------------------------------------------------------
 * - How to limit results with offset
 */
@Component
class LimitOffset : ServiceBase<List<Customer>>() {
    override fun call(): List<Customer> =
        db.select(db.tabCustomer)
            .whereGreater(db.tabCustomer.colID(), 10)
            .limitBy(5, 13)
            .findMany()

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(DemoApplication::class.java, LimitOffset::class.java.name)
        }
    }
}
