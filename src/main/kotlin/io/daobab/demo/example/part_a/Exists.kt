package io.daobab.demo.example.part_a

import io.daobab.demo.DemoApplication
import io.daobab.demo.base.ServiceBase
import org.springframework.boot.SpringApplication
import org.springframework.stereotype.Component

/**
 * ---------------------------------------------------------
 * - How to check if the record exists
 * ---------------------------------------------------------
 */
@Component
class Exists : ServiceBase<Boolean>() {
    //Only one record is being retrieved from the database
    override fun call() =
        db.select(db.tabCustomer)
            .whereEqual(db.tabCustomer.colID(), 10)
            .exists()


    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(DemoApplication::class.java, Exists::class.java.name)
        }
    }
}
