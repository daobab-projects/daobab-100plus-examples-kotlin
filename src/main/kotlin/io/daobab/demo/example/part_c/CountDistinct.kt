package io.daobab.demo.example.part_c

import io.daobab.demo.DemoApplication
import io.daobab.demo.base.ServiceBase
import io.daobab.statement.function.FunctionWhispererH2
import org.springframework.boot.SpringApplication
import org.springframework.stereotype.Component

/**
 * ---------------------------------------------------------
 * Count Distinct
 * ---------------------------------------------------------
 */
@Component
class CountDistinct : ServiceBase<Long>(), FunctionWhispererH2 {

    override fun call(): Long =
        db.select(count(distinct(db.tabCustomer.colFirstName())))
            .whereGreater(db.tabCustomer.colID(), 10)
            .findOne()

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(DemoApplication::class.java, CountDistinct::class.java.name)
        }
    }
}
