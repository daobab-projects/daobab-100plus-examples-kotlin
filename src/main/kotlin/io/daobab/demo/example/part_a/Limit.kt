package io.daobab.demo.example.part_a

import io.daobab.demo.DemoApplication
import io.daobab.demo.base.ServiceBase
import io.daobab.demo.dao.table.Customer
import org.springframework.boot.SpringApplication
import org.springframework.stereotype.Component

/**
 * ---------------------------------------------------------
 * - How to limit results
 * ---------------------------------------------------------
 */
@Component
class Limit : ServiceBase<List<Customer>>() {
    override fun call(): List<Customer> =
        db.select(db.tabCustomer)
            .whereGreater(db.tabCustomer.colID(), 1)
            .limitBy(5)
            .findMany()


    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(DemoApplication::class.java, Limit::class.java.name)
        }
    }
}
