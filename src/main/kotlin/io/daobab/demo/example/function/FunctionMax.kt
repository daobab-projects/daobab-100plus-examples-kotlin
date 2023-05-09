package io.daobab.demo.example.function

import io.daobab.demo.DemoApplication
import io.daobab.demo.base.ServiceBase
import io.daobab.statement.function.FunctionWhispererH2
import org.springframework.boot.SpringApplication
import org.springframework.stereotype.Component

/**
 * ---------------------------------------------------------
 * Max
 * ---------------------------------------------------------
 */
@Component
class FunctionMax : ServiceBase<Int>(), FunctionWhispererH2 {
    override fun call(): Int =
        db.select(max(length(db.tabFilm.colDescription())))
            .groupBy(db.tabFilm.colLanguageId())
            .findOne()


    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(DemoApplication::class.java, FunctionMax::class.java.name)
        }
    }
}
