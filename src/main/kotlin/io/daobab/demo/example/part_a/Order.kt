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
 * How to use Order
 * ---------------------------------------------------------
 * - order clause
 * - order by many columns
 * - order asc and desc
 */
@Component
class Order : ServiceBase<Entities<Customer>>(), QueryWhisperer {
    override fun call() = way01()

    //default order (asc)
    fun way01(): Entities<Customer> {
        val t = db.tabCustomer
        return db.select(t)
            .whereLike(t.colFirstName(), "%S%")
            .orderBy(t.colLastName())
            .findMany()
    }

    //order asc
    fun way02(): Entities<Customer> {
        val t = db.tabCustomer
        return db.select(t)
            .whereLike(t.colFirstName(), "%S%")
            .orderBy(asc(t.colLastName()))
            .findMany()
    }

    //order by many columns
    fun way03(): Entities<Customer> {
        val t = db.tabCustomer
        return db.select(t)
            .whereLike(t.colFirstName(), "%S%")
            .orderBy(asc(t.colLastName()).desc(t.colLastName()).asc(t.colEmail()))
            .findMany()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(DemoApplication::class.java, Order::class.java.name)
        }
    }
}
