package io.daobab.demo.example.part_d

import io.daobab.demo.DemoApplication
import io.daobab.demo.base.ServiceBase
import io.daobab.demo.dao.table.Payment
import io.daobab.demo.example.part_d.RemoteSecurityForbiddenEntities
import org.springframework.boot.SpringApplication
import org.springframework.stereotype.Component

/**
 * ---------------------------------------------------------
 * Remote Security Forbidden Entities
 * ---------------------------------------------------------
 * - How to prevent from select forbidden entities
 */
@Component
class RemoteSecurityForbiddenEntities : ServiceBase<List<Payment>>() {
    override fun call(): List<Payment> {
        val t = db.tabPayment
        return remote.select(t)
            .whereLess(t.colID(), 10)
            .findMany()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(DemoApplication::class.java, RemoteSecurityForbiddenEntities::class.java.name)
        }
    }
}
