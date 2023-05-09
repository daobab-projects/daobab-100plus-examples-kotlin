package io.daobab.demo.example.part_d

import io.daobab.demo.DemoApplication
import io.daobab.demo.base.ServiceBase
import io.daobab.demo.dao.table.Customer
import org.springframework.boot.SpringApplication
import org.springframework.stereotype.Component

/**
 * ---------------------------------------------------------
 * Remote Security - Forbidden Columns
 * ---------------------------------------------------------
 * - How to prevent from select some columns
 */
@Component
class RemoteSecurityForbiddenColumns : ServiceBase<List<Customer>>() {
    override fun call(): List<Customer> {
        val t = db.tabCustomer
        return remote.select(t)
            .whereLess(t.colAddressId(), 10)
            .findMany()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(DemoApplication::class.java, RemoteSecurityForbiddenColumns::class.java.name)
        }
    }
}
