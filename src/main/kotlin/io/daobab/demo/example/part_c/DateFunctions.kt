package io.daobab.demo.example.part_c

import io.daobab.demo.DemoApplication
import io.daobab.demo.base.ServiceBase
import io.daobab.model.Plate
import io.daobab.statement.function.FunctionWhispererH2
import org.springframework.boot.SpringApplication
import org.springframework.stereotype.Component

/**
 * ---------------------------------------------------------
 * Date Functions
 * ---------------------------------------------------------
 */
@Component
class DateFunctions : ServiceBase<Plate>(), FunctionWhispererH2 {

    override fun call(): Plate {
        val f = db.tabPayment
        return db.select(dayOfMonth(f.colPaymentDate()), month(f.colPaymentDate()), year(f.colPaymentDate()))
            .whereEqual(f.colID(), 15)
            .findOne()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(DemoApplication::class.java, DateFunctions::class.java.name)
        }
    }
}
