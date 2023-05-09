package io.daobab.demo.example.part_c

import io.daobab.demo.DemoApplication
import io.daobab.demo.base.ServiceBase
import io.daobab.demo.dao.table.Payment
import io.daobab.query.base.QueryWhisperer
import io.daobab.target.buffer.single.Entities
import org.springframework.boot.SpringApplication
import org.springframework.stereotype.Component

/**
 * ---------------------------------------------------------
 * Operator NotNull / IsNull
 * ---------------------------------------------------------
 */
@Component
class WhereNotNullIsNull : ServiceBase<Entities<Payment>>(), QueryWhisperer {
    override fun call(): Entities<Payment> {
        val p = db.tabPayment
        return db.select(p)
            .where(
                and()
                    .notNull(p.colPaymentDate())
                    .isNull(p.colAmount())
            )
            .limitBy(5)
            .findMany()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(DemoApplication::class.java, WhereNotNullIsNull::class.java.name)
        }
    }
}
