package io.daobab.demo.example.function

import io.daobab.demo.DemoApplication
import io.daobab.demo.base.ServiceBase
import io.daobab.statement.function.FunctionWhispererH2
import org.springframework.boot.SpringApplication
import org.springframework.stereotype.Component
import java.math.BigDecimal

/**
 * ---------------------------------------------------------
 * Sum
 * ---------------------------------------------------------
 */
@Component
class FunctionSum : ServiceBase<List<BigDecimal>>(), FunctionWhispererH2 {
    override fun call(): List<BigDecimal> =
        db.select(sum(db.tabFilm.colLength(), BigDecimal::class.java))
            .groupBy(db.tabFilm.colLanguageId())
            .findMany()


    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(DemoApplication::class.java, FunctionSum::class.java.name)
        }
    }
}
