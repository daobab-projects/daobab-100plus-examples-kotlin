package io.daobab.demo.example.function

import io.daobab.demo.DemoApplication
import io.daobab.demo.base.ServiceBase
import io.daobab.statement.function.FunctionWhispererH2
import org.springframework.boot.SpringApplication
import org.springframework.stereotype.Component

/**
 * ---------------------------------------------------------
 * Function DayOfYear
 * ---------------------------------------------------------
 */
@Component
class FunctionDayOfYear : ServiceBase<Int>(), FunctionWhispererH2 {
    override fun call(): Int =
        db.select(dayOfYear(db.tabCountry.colLastUpdate())).findOne()


    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(DemoApplication::class.java, FunctionDayOfYear::class.java.name)
        }
    }
}
