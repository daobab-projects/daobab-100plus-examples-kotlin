package io.daobab.demo.example.function

import io.daobab.demo.DemoApplication
import io.daobab.demo.base.ServiceBase
import io.daobab.statement.function.FunctionWhispererH2
import org.springframework.boot.SpringApplication
import org.springframework.stereotype.Component

/**
 * ---------------------------------------------------------
 * Function CurrentDate2
 * ---------------------------------------------------------
 */
@Component
class FunctionCurrentDate2 : ServiceBase<String>(), FunctionWhispererH2 {
    override fun call(): String {
        return "" //db.select(substring("The time now is: ",currentDate().cast(String.class))).findOne();
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(DemoApplication::class.java, FunctionCurrentDate2::class.java.name)
        }
    }
}
