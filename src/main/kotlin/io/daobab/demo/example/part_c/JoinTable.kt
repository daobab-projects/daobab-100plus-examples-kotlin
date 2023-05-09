package io.daobab.demo.example.part_c

import io.daobab.demo.DemoApplication
import io.daobab.demo.base.ServiceBase
import io.daobab.demo.example.part_c.JoinTable
import io.daobab.result.FlatPlates
import org.springframework.boot.SpringApplication
import org.springframework.stereotype.Component

/**
 * ---------------------------------------------------------
 * Join Table
 * ---------------------------------------------------------
 * - How to join tables by the same column (PK,FK)
 */
@Component
class JoinTable : ServiceBase<FlatPlates>() {
    override fun call(): FlatPlates =
        db.select(db.tabCustomer.colFirstName(), db.tabCustomer.colLastName(), db.tabAddress.colPhone())
            .join(db.tabAddress, db.tabCustomer.colAddressId())
            .findManyAsFlat()


    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(DemoApplication::class.java, JoinTable::class.java.name)
        }
    }
}
