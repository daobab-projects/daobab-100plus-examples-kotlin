package io.daobab.demo.example.function

import io.daobab.demo.DemoApplication
import io.daobab.demo.base.ServiceBase
import io.daobab.statement.function.FunctionWhispererH2
import org.springframework.boot.SpringApplication
import org.springframework.stereotype.Component

/**
 * ---------------------------------------------------------
 * Function Lower
 * ---------------------------------------------------------
 */
@Component
class FunctionLower : ServiceBase<List<String?>?>(), FunctionWhispererH2 {
    override fun call(): List<String> {
        val t = db.tabCountry
        return db.select(lower(t.colCountry()))
            .whereBetween(t.colID(), 10, 20)
            .findMany()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(DemoApplication::class.java, FunctionLower::class.java.name)
        }
    }
}
