package io.daobab.demo.example.function

import io.daobab.demo.DemoApplication
import io.daobab.demo.base.ServiceBase
import io.daobab.model.RelatedTo
import io.daobab.model.Table
import io.daobab.statement.function.FunctionWhispererH2
import org.springframework.boot.SpringApplication
import org.springframework.stereotype.Component
import java.sql.Timestamp

/**
 * ---------------------------------------------------------
 * Function CurrentDate
 * ---------------------------------------------------------
 */
@Component
class FunctionCurrentDate : ServiceBase<Timestamp>(), FunctionWhispererH2 {
    override fun call(): Timestamp =
        db.select(currentDate<Table<*>, Any, RelatedTo<*>>()).findOne()


    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(DemoApplication::class.java, FunctionCurrentDate::class.java.name)
        }
    }
}
