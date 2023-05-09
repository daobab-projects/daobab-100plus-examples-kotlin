package io.daobab.demo.example.part_c

import io.daobab.demo.DemoApplication
import io.daobab.demo.base.ServiceBase
import io.daobab.result.FlatPlates
import org.springframework.boot.SpringApplication
import org.springframework.stereotype.Component

/**
 * ---------------------------------------------------------
 * Join Table by PrimaryKey
 * ---------------------------------------------------------
 * - How to join table by their PK
 */
@Component
class JoinPrimaryKeyTable : ServiceBase<FlatPlates>() {
    override fun call(): FlatPlates =
        db.select(db.tabCustomer.colFirstName(), db.tabCustomer.colLastName(), db.tabAddress.colPhone())
            .joinPk(db.tabAddress)
            .limitBy(10)
            .findManyAsFlat()

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(DemoApplication::class.java, JoinPrimaryKeyTable::class.java.name)
        }
    }
}
