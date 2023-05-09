package io.daobab.demo.example.part_c

import io.daobab.demo.DemoApplication
import io.daobab.demo.base.ServiceBase
import io.daobab.demo.dao.table.Customer
import io.daobab.query.base.QueryWhisperer
import io.daobab.target.buffer.single.Entities
import org.springframework.boot.SpringApplication
import org.springframework.stereotype.Component

/**
 * ---------------------------------------------------------
 * Where AND and OR combined
 * ---------------------------------------------------------
 * - How to safety combine different Where clauses
 */
@Component
class WhereManyCombined : ServiceBase<Entities<Customer>>(), QueryWhisperer {

    override fun call(): Entities<Customer> {
        val t = db.tabCustomer
        return db.select(t)
            .where(
                or()
                    .or(
                        and()
                            .greaterOrEqual(t.colID(), 5)
                            .less(t.colID(), 10)
                    ).or(
                        and()
                            .greaterOrEqual(t.colID(), 15)
                            .less(t.colID(), 20)
                    )
            )
            .findMany()
    }


    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(DemoApplication::class.java, WhereManyCombined::class.java.name)
        }
    }
}
