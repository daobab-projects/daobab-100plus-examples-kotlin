package io.daobab.demo.example.function

import io.daobab.demo.DemoApplication
import io.daobab.demo.base.ServiceBase
import io.daobab.statement.function.FunctionWhispererH2
import org.springframework.boot.SpringApplication
import org.springframework.stereotype.Component
import java.math.BigDecimal

/**
 * ---------------------------------------------------------
 * Avg
 * ---------------------------------------------------------
 */
@Component
class FunctionAvg : ServiceBase<List<BigDecimal>>(), FunctionWhispererH2 {
    override fun call(): List<BigDecimal> =
        db.select(avg(db.tabFilm.colLength()))
            .groupBy(db.tabFilm.colLanguageId())
            .findMany()

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(DemoApplication::class.java, FunctionAvg::class.java.name)
        }
    }
}
