package io.daobab.demo.example.part_a

import io.daobab.demo.DemoApplication
import io.daobab.demo.base.ServiceBase
import io.daobab.demo.dao.table.Customer
import io.daobab.query.base.QueryWhisperer
import io.daobab.target.buffer.single.Entities
import org.springframework.boot.SpringApplication
import org.springframework.stereotype.Component

/**
 * ---------------------------------------------------------
 * - How to use WhereNOT clause
 * ---------------------------------------------------------
 * - Where clauses like and() or() not() prevents from committing
 * logical errors
 */
@Component
class WhereNot : ServiceBase<Entities<Customer>>(), QueryWhisperer {
    override fun call(): Entities<Customer> {
        val t = db.tabCustomer
        return db.select(t)
            .where(not().equal(t.colActive(), true))
            .limitBy(5)
            .findMany()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(DemoApplication::class.java, WhereNot::class.java.name)
        }
    }
}
