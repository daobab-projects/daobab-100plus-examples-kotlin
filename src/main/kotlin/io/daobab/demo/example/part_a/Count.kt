package io.daobab.demo.example.part_a

import io.daobab.demo.DemoApplication
import io.daobab.demo.base.ServiceBase
import io.daobab.demo.dao.table.Customer
import io.daobab.statement.function.FunctionWhispererH2
import org.springframework.boot.SpringApplication
import org.springframework.stereotype.Component

/**
 * ---------------------------------------------------------
 * - How to count
 * ---------------------------------------------------------
 */
@Component
class Count : ServiceBase<Long>(), FunctionWhispererH2 {
    override fun call(): Long {
        return db.select(count(db.tabCustomer)).whereGreater(db.tabCustomer.colID(), 10).findOne()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(DemoApplication::class.java, Count::class.java.name)
        }
    }
}
