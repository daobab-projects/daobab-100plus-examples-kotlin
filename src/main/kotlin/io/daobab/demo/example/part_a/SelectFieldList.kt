package io.daobab.demo.example.part_a

import io.daobab.demo.DemoApplication
import io.daobab.demo.base.ServiceBase
import org.springframework.boot.SpringApplication
import org.springframework.stereotype.Component

/**
 * ---------------------------------------------------------
 * - How to get values of particular column
 * ---------------------------------------------------------
 */
@Component
class SelectFieldList : ServiceBase<List<String>>() {
    override fun call(): List<String> {
        val c = db.tabCustomer
        return db.select(c.colFirstName())
            .whereLess(c.colID(), 10)
            .findMany()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(DemoApplication::class.java, SelectFieldList::class.java.name)
        }
    }
}
