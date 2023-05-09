package io.daobab.demo.example.part_b

import io.daobab.demo.DemoApplication
import io.daobab.demo.base.ServiceBase
import io.daobab.demo.dao.table.Customer
import io.daobab.demo.example.part_b.PlateAsEntity
import io.daobab.query.base.QueryWhisperer
import org.springframework.boot.SpringApplication
import org.springframework.stereotype.Component

/**
 * ---------------------------------------------------------
 * Select As Entity
 * ---------------------------------------------------------
 * - How to get just specific columns only from single row of particular table wrapped in single Entity
 */
@Component
class PlateAsEntity : ServiceBase<Customer>(), QueryWhisperer {

    override fun call(): Customer {
        val customer = db.tabCustomer
        return db.select(customer.colFirstName(), customer.colLastName())
            .where(
                and()
                    .equal(customer.colActive(), true)
                    .equal(customer.colFirstName(), "SUSAN")
                    .equal(customer.colLastName(), "WILSON")
            )
            .findOneAs(customer::class.java)
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(DemoApplication::class.java, PlateAsEntity::class.java.name)
        }
    }
}
