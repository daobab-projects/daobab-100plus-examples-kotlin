package io.daobab.demo.example.part_e

import io.daobab.demo.DemoApplication
import io.daobab.demo.base.ServiceBase
import org.springframework.boot.SpringApplication
import org.springframework.stereotype.Component

/**
 * ---------------------------------------------------------
 * OCC
 * ---------------------------------------------------------
 * - How to use Optimistic Concurrency Control
 */
@Component
class OptimisticConcurrencyControlForPrimaryKey : ServiceBase<Unit>() {
    override fun call() {
        val f = db.tabStaff
        val staff = db.findOneByPk(f, 1)
        staff.update(db, f.colEmail())
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(
                DemoApplication::class.java,
                OptimisticConcurrencyControlForPrimaryKey::class.java.name
            )
        }
    }
}
