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
 * - How to use WhereOR clause
 * ---------------------------------------------------------
 * - Where clauses like and() or() not() prevents from committing
 * logical errors
 */
@Component
class WhereOr : ServiceBase<Entities<Customer>>(), QueryWhisperer {
    override fun call(): Entities<Customer> {
        return way01()
    }

    fun way01(): Entities<Customer> {
        val t = db.tabCustomer
        return db.select(t)
            .where(
                or()
                    .equal(t.colFirstName(), "SUSAN")
                    .equal(t.colLastName(), "ANDERSON")
            )
            .findMany()
    }

    fun way02(): Entities<Customer> {
        val t = db.tabCustomer
        return db.select(t)
            .whereOr {
                it.equal(t.colFirstName(), "SUSAN")
                    .equal(t.colLastName(), "ANDERSON")
            }
            .findMany()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(DemoApplication::class.java, WhereOr::class.java.name)
        }
    }
}
