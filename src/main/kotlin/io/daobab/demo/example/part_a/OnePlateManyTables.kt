package io.daobab.demo.example.part_a

import io.daobab.demo.DemoApplication
import io.daobab.demo.base.ServiceBase
import io.daobab.model.Column
import io.daobab.model.Plate
import org.springframework.boot.SpringApplication
import org.springframework.stereotype.Component

/**
 * ---------------------------------------------------------
 * - How to get specific columns from a one row
 * ---------------------------------------------------------
 */
@Component
class OnePlateManyTables : ServiceBase<Plate>() {
    override fun call(): Plate {
        val t = db.tabCustomer
        val a = db.tabAddress
        val rv: Plate = db.select(t.colFirstName(), t.colLastName(), a.colAddress())
            .join(a, t.colAddressId())
            .whereAnd {
                it
                    .equal(t.colActive(), true)
                    .equal(t.colFirstName(), "SUSAN")
                    .equal(t.colLastName(), "WILSON")
            }.findOne()
        logResult(rv, t.colFirstName(), t.colLastName(), a.colAddress())
        return rv
    }

    private fun logResult(plate: Plate, vararg col: Column<*, *, *>) {
        for (c in col) {
            log.info("entity:" + c.entityClass().simpleName + ",column:" + c.columnName + ",value:" + plate.getValue(c))
        }
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(DemoApplication::class.java, OnePlateManyTables::class.java.name)
        }
    }
}
