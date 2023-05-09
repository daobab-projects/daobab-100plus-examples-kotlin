package io.daobab.demo.example.part_c

import io.daobab.demo.DemoApplication
import io.daobab.demo.base.ServiceBase
import io.daobab.demo.example.part_c.InnerSelect
import io.daobab.result.FlatPlates
import org.springframework.boot.SpringApplication
import org.springframework.stereotype.Component

/**
 * ---------------------------------------------------------
 * Inner Select
 * ---------------------------------------------------------
 * - How to use inner selects
 */
@Component
class InnerSelect : ServiceBase<FlatPlates>() {
    override fun call() = way01().onEach { log.info(it.toString()) }


    fun way01(): FlatPlates {
        val c = db.tabCustomer
        val a = db.tabAddress
        return db.select(c.colFirstName(), c.colLastName(), a.colPhone())
            .whereIn(
                c.colAddressId(),
                db.select(a.colAddressId()).whereEqual(a.colPostalCode(), "85505")
            )
            .findManyAsFlat()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(DemoApplication::class.java, InnerSelect::class.java.name)
        }
    }
}