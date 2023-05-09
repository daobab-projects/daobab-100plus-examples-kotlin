package io.daobab.demo.example.part_e

import io.daobab.demo.DemoApplication
import io.daobab.demo.base.ServiceBase
import io.daobab.demo.dao.table.Payment
import io.daobab.parser.ParserGeneral
import org.springframework.boot.SpringApplication
import org.springframework.stereotype.Component

/**
 * ---------------------------------------------------------
 * Eager Loading
 * ---------------------------------------------------------
 * - How to use related objects Eager Loading
 */
@Component
class EagerLoading : ServiceBase<List<Payment>>(), ParserGeneral {
    override fun call(): List<Payment>  = db.select(db.tabPayment)
            .whereEqual(db.tabPayment.colAmount(), toBigDecimal(100))
            .orderAscBy(db.tabPayment.colPaymentDate())
            .findMany().onEach { resultPostProcessor(it) }

    private fun resultPostProcessor(payment: Payment) {
        payment["relatedCustomer"] = payment.findRelatedOne(db, db.tabCustomer)
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(DemoApplication::class.java, EagerLoading::class.java.name)
        }
    }
}
