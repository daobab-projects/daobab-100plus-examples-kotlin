package io.daobab.demo.example.part_a

import io.daobab.demo.DemoApplication
import io.daobab.demo.base.ServiceBase
import org.springframework.boot.SpringApplication
import org.springframework.stereotype.Component

/**
 * ---------------------------------------------------------
 * - How to get single value of particular column
 * ---------------------------------------------------------
 */
@Component
class SelectField : ServiceBase<String?>() {
    override fun call(): String {
        val c = db.tabCustomer
        return db.select(c.colFirstName())
            .whereEqual(c.colID(), 10)
            .findOne()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(DemoApplication::class.java, SelectField::class.java.name)
        }
    }
}
