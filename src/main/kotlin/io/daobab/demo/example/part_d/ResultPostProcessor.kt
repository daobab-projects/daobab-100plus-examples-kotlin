package io.daobab.demo.example.part_d

import io.daobab.demo.DemoApplication
import io.daobab.demo.base.ServiceBase
import io.daobab.demo.dao.table.Payment
import io.daobab.parser.ParserGeneral
import org.springframework.boot.SpringApplication
import org.springframework.stereotype.Component
import java.math.BigDecimal

/**
 * ---------------------------------------------------------
 * PostProcessor
 * ---------------------------------------------------------
 * - How to use PostProcessor
 */
@Component
class ResultPostProcessor : ServiceBase<List<Payment>>(), ParserGeneral {
    override fun call(): List<Payment> = db.select(db.tabPayment)
            .whereEqual(db.tabPayment.colAmount(), toBigDecimal(100))
            .orderAscBy(db.tabPayment.colPaymentDate())
            .findMany()
            .onEach { myPostProcess(it) }

    private fun myPostProcess(payment: Payment) {
        payment.put("amount_GT_200", payment.getAmount() > BigDecimal(200))
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(DemoApplication::class.java, ResultPostProcessor::class.java.name)
        }
    }
}
