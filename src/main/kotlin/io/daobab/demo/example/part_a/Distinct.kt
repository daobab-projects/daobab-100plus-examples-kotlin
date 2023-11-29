package io.daobab.demo.example.part_a

import io.daobab.demo.DemoApplication
import io.daobab.demo.base.ServiceBase
import io.daobab.statement.function.FunctionWhispererH2
import org.springframework.boot.SpringApplication
import org.springframework.stereotype.Component

/**
 * ---------------------------------------------------------
 * - How to use distinct
 * ---------------------------------------------------------
 */
@Component
class Distinct : ServiceBase<List<String>>(), FunctionWhispererH2 {
    override fun call(): List<String> {
        val tab = db.tabCustomer
        return db.select(distinct(tab.colFirstName()))
            .orderAscBy(tab.colFirstName())
            .limitBy(15)
            .findMany()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(DemoApplication::class.java, Distinct::class.java.name)
        }
    }
}
