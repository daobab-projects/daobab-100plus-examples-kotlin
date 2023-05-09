package io.daobab.demo.example.function

import io.daobab.demo.DemoApplication
import io.daobab.demo.base.ServiceBase
import io.daobab.statement.function.FunctionWhispererH2
import org.springframework.boot.SpringApplication
import org.springframework.stereotype.Component

/**
 * ---------------------------------------------------------
 * Function Camel
 * ---------------------------------------------------------
 */
@Component
class FunctionCamel : ServiceBase<List<String>>(), FunctionWhispererH2 {
    override fun call(): List<String> {
        val t = db.tabCountry
        return db.select(camel(t.colCountry()))
            .whereBetween(t.colID(), 10, 20)
            .findMany()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(DemoApplication::class.java, FunctionCamel::class.java.name)
        }
    }
}
