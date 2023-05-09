package io.daobab.demo.example.function

import io.daobab.demo.DemoApplication
import io.daobab.demo.base.ServiceBase
import io.daobab.statement.function.FunctionWhispererH2
import org.springframework.boot.SpringApplication
import org.springframework.stereotype.Component

/**
 * ---------------------------------------------------------
 * Function Concat2
 * ---------------------------------------------------------
 */
@Component
class FunctionConcat2 : ServiceBase<List<String>>(), FunctionWhispererH2 {
    override fun call(): List<String> {
        val t = db.tabCountry
        return db.select(concat(upper(t.colCountry()), lower(t.colCountry())))
            .whereBetween(t.colID(), 10, 20)
            .findMany()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(DemoApplication::class.java, FunctionConcat2::class.java.name)
        }
    }
}
