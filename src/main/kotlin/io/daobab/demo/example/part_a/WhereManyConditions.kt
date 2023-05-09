package io.daobab.demo.example.part_a

import io.daobab.demo.DemoApplication
import io.daobab.demo.base.ServiceBase
import io.daobab.demo.dao.table.Customer
import io.daobab.demo.example.part_a.WhereManyConditions
import io.daobab.query.base.QueryWhisperer
import org.springframework.boot.SpringApplication
import org.springframework.stereotype.Component

/**
 * ---------------------------------------------------------
 * - How to use where clause with many conditions
 * ---------------------------------------------------------
 */
@Component
class WhereManyConditions : ServiceBase<Customer>(), QueryWhisperer {
    override fun call() = way01()

    //Classic usage
    fun way01(): Customer {
        return db.select(db.tabCustomer)
            .where(
                and()
                    .equal(db.tabCustomer.colActive(), true)
                    .equal(db.tabCustomer.colFirstName(), "SUSAN")
                    .equal(db.tabCustomer.colLastName(), "WILSON")
            )
            .findOne()
    }

    //exposing reference
    fun way02(): Customer {
        val t = db.tabCustomer
        return db.select(t)
            .where(
                and()
                    .equal(t.colActive(), true)
                    .equal(t.colFirstName(), "SUSAN")
                    .equal(t.colLastName(), "WILSON")
            )
            .findOne()
    }

    //functional
    fun way03(): Customer {
        val t = db.tabCustomer
        return db.select(t)
            .whereAnd {
                it.equal(t.colActive(), true)
                    .equal(t.colFirstName(), "SUSAN")
                    .equal(t.colLastName(), "WILSON")
            }
            .findOne()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(DemoApplication::class.java, WhereManyConditions::class.java.name)
        }
    }
}
