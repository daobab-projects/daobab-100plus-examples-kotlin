package io.daobab.demo.example.part_c

import io.daobab.demo.DemoApplication
import io.daobab.demo.base.ServiceBase
import io.daobab.model.FlatPlate
import io.daobab.query.base.QueryWhisperer
import org.springframework.boot.SpringApplication
import org.springframework.stereotype.Component

/**
 * ---------------------------------------------------------
 * Select FlatPlate from Many Tables
 * ---------------------------------------------------------
 * - How to get specific columns from many tables, for one rows
 * but by FlatPlate
 * ---------------------------------------------------------
 * - FlatPlate can be used when all columns selected have unique name
 * - Plate is able to handle many columns with the same name,
 * belonging to different Entities
 */
@Component
class FlatPlateExample : ServiceBase<FlatPlate>(), QueryWhisperer {
    override fun call(): FlatPlate {
        val t = db.tabCustomer
        val a = db.tabAddress
        return db.select(t.colFirstName(), t.colLastName(), a.colAddress())
            .join(a, t.colAddressId())
            .where(
                and()
                    .equal(t.colActive(), true)
                    .equal(t.colFirstName(), "SUSAN")
                    .equal(t.colLastName(), "WILSON")
            )
            .flat() //<-enabling flat
            .findOne()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(DemoApplication::class.java, FlatPlateExample::class.java.name)
        }
    }
}
