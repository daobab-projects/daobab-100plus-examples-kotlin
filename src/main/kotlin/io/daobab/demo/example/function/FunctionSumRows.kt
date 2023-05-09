package io.daobab.demo.example.function

import io.daobab.demo.DemoApplication
import io.daobab.demo.base.ServiceBase
import io.daobab.statement.function.FunctionWhispererH2
import org.springframework.boot.SpringApplication
import org.springframework.stereotype.Component
import java.math.BigDecimal

/**
 * ---------------------------------------------------------
 * SumRows
 * ---------------------------------------------------------
 */
@Component
class FunctionSumRows : ServiceBase<List<BigDecimal>>(), FunctionWhispererH2 {
    override fun call(): List<BigDecimal> {
        return db.select(
            sumRows(
                db.tabFilm.colRentalRate(),
                db.tabFilm.colReplacementCost()
            ).cast(BigDecimal::class.java)
        )
            .groupBy(db.tabFilm.colLanguageId())
            .findMany()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(DemoApplication::class.java, FunctionSumRows::class.java.name)
        }
    }
}
