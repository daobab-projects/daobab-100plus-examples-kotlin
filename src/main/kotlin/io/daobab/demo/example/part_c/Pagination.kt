package io.daobab.demo.example.part_c

import io.daobab.demo.DemoApplication
import io.daobab.demo.base.ServiceBase
import io.daobab.demo.dao.table.Customer
import org.springframework.boot.SpringApplication
import org.springframework.stereotype.Component

/**
 * ---------------------------------------------------------
 * Pagination
 * ---------------------------------------------------------
 */
@Component
class Pagination : ServiceBase<List<Customer>>() {

    override fun call(): List<Customer> {
        val c = db.tabCustomer
        return db.select(c)
            .whereGreater(c.colID(), 10)
            .page(2, 20)
            .findMany()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(DemoApplication::class.java, Pagination::class.java.name)
        }
    }
}
