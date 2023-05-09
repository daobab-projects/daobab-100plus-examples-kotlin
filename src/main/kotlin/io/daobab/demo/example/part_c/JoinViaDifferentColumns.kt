package io.daobab.demo.example.part_c

import io.daobab.demo.DemoApplication
import io.daobab.demo.base.ServiceBase
import io.daobab.demo.example.part_c.JoinViaDifferentColumns
import io.daobab.model.FlatPlate
import io.daobab.query.base.QueryWhisperer
import org.springframework.boot.SpringApplication
import org.springframework.stereotype.Component

/**
 * ---------------------------------------------------------
 * Join by different columns
 * ---------------------------------------------------------
 * - How to use joins linking different columns
 */
@Component
class JoinViaDifferentColumns : ServiceBase<List<FlatPlate>>(), QueryWhisperer {
    override fun call(): List<FlatPlate> {
        return way01()
            .onEach { log.info(it.toString()) }
    }

    fun way01(): List<FlatPlate> {
        val c = db.tabCustomer
        val a = db.tabAddress
        return db.select(
            c.colFirstName(),
            c.colLastName(),
            a.colPhone()
        ) //specify tables to join from left and right table
            .join(a, joinOn(a.colAddressId(), c.colAddressId()))
            .flat()
            .findMany()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(DemoApplication::class.java, JoinViaDifferentColumns::class.java.name)
        }
    }
}
