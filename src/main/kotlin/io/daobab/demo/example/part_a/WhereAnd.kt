package io.daobab.demo.example.part_a

import io.daobab.demo.DemoApplication
import io.daobab.demo.base.ServiceBase
import io.daobab.demo.dao.table.Customer
import io.daobab.query.base.QueryWhisperer
import io.daobab.target.buffer.single.Entities
import org.springframework.boot.SpringApplication
import org.springframework.stereotype.Component
import java.time.LocalDate

/**
 * ---------------------------------------------------------
 * - How to use WhereAND clause
 * ---------------------------------------------------------
 * - Where clauses like and() or() not() prevents from committing
 * logical errors
 */
@Component
class WhereAnd : ServiceBase<Entities<Customer>>(), QueryWhisperer {
    override fun call(): Entities<Customer> = way01()

    fun way01(): Entities<Customer> {
        val t = db.tabCustomer
        return db.select(db.tabCustomer)
            .where(
                and()
                    .equal(t.colActive(), true)
                    .equal(t.colFirstName(), "SUSAN")
                    .equal(t.colLastName(), "WILSON")
                    .notNull(t.colAddressId())
                    .notEqual(t.colEmail(), "forbidden@email.com")
                    .greater(t.colCustomerId(), 0)
                    .between(
                        t.colCreateDate(),
                        LocalDate.parse("2005-01-01").atStartOfDay(),
                        LocalDate.parse("2007-01-01").atStartOfDay()
                    )
            )
            .findMany()
    }

    //Functional approach
    fun way02(): Entities<Customer> {
        val t = db.tabCustomer
        return db.select(t)
            .whereAnd { it
                    .equal(t.colActive(), true)
                    .equal(t.colFirstName(), "SUSAN")
                    .equal(t.colLastName(), "WILSON")
                    .notNull(t.colAddressId())
                    .notEqual(t.colEmail(), "forbidden@email.com")
                    .greater(t.colCustomerId(), 0)
                    .between(
                        t.colCreateDate(),
                        LocalDate.parse("2005-01-01").atStartOfDay(),
                        LocalDate.parse("2007-01-01").atStartOfDay()
                    )
            }
            .findMany()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(DemoApplication::class.java, WhereAnd::class.java.name)
        }
    }
}
