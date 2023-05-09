package io.daobab.demo.example.part_b

import io.daobab.demo.DemoApplication
import io.daobab.demo.base.ServiceBase
import io.daobab.demo.dao.table.Payment
import io.daobab.demo.example.part_b.ResultConsumer
import io.daobab.parser.ParserGeneral
import org.springframework.boot.SpringApplication
import org.springframework.stereotype.Component

/**
 * ---------------------------------------------------------
 * - How to use Result Consumer
 * ---------------------------------------------------------
 */
@Component
class ResultConsumer : ServiceBase<Unit>(), ParserGeneral {
    override fun call() {
        db.select(db.tabPayment)
            .whereEqual(db.tabPayment.colAmount(), toBigDecimal(100))
            .orderAscBy(db.tabPayment.colPaymentDate())
            .findMany()
            .forEach { resultConsumer(it) }
    }

    private fun resultConsumer(payment: Payment) {
        log.info("amount: " + payment.getAmount() + " received at " + payment.getPaymentDate())
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(DemoApplication::class.java, ResultConsumer::class.java.name)
        }
    }
}
