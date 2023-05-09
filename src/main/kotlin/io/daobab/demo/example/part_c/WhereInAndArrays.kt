package io.daobab.demo.example.part_c

import io.daobab.demo.DemoApplication
import io.daobab.demo.base.ServiceBase
import io.daobab.demo.dao.table.Customer
import io.daobab.target.buffer.single.Entities
import org.springframework.boot.SpringApplication
import org.springframework.stereotype.Component

/**
 * ---------------------------------------------------------
 * Operator IN and Arrays
 * ---------------------------------------------------------
 * - How to use IN and Arrays
 */
@Component
class WhereInAndArrays : ServiceBase<Entities<Customer>>() {
    override fun call(): Entities<Customer> {
        val p = db.tabCustomer
        return db.select(p)
            .whereIn(
                p.colFirstName(),
                "BARBARA", "HELEN", "NANCY", "SUSAN", "MARGARET", "MARY"
            )
            .limitBy(5)
            .findMany()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(DemoApplication::class.java, WhereInAndArrays::class.java.name)
        }
    }
}
