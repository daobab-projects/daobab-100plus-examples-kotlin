package io.daobab.demo.example.part_b

import io.daobab.demo.DemoApplication
import io.daobab.demo.base.ServiceBase
import io.daobab.statement.function.FunctionWhispererH2
import org.springframework.boot.SpringApplication
import org.springframework.stereotype.Component

/**
 * ---------------------------------------------------------
 * Distinct Count
 * ---------------------------------------------------------
 */
@Component
class DistinctCount : ServiceBase<Long>(), FunctionWhispererH2 {
    override fun call(): Long =
        db.select(db.tabCustomer.colFirstName())
            .distinct()
            .countAny()

    fun way02(): Long = db.select(count(distinct(db.tabCustomer.colFirstName()))).findOne()


    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(DemoApplication::class.java, DistinctCount::class.java.name)
        }
    }
}
