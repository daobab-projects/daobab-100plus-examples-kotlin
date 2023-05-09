package io.daobab.demo.example.part_c

import io.daobab.demo.DemoApplication
import io.daobab.demo.base.ServiceBase
import io.daobab.demo.example.part_c.JoinManyTables
import io.daobab.result.FlatPlates
import org.springframework.boot.SpringApplication
import org.springframework.stereotype.Component

/**
 * ---------------------------------------------------------
 * Join Many Tables
 * ---------------------------------------------------------
 * - How to join multiple tables by the same column (PK,FK)
 */
@Component
class JoinManyTables : ServiceBase<FlatPlates>() {
    override fun call(): FlatPlates {
        return way01()
            .onEach { log.info(it.toString()) }
    }

    fun way01(): FlatPlates {
        val c = db.tabCustomer
        val a = db.tabAddress
        val city = db.tabCity
        val country = db.tabCountry
        return db.select(
            c.colFirstName(), c.colLastName(), a.colPhone(), city.colCity(), country.colCountry()
        )
            .join(a, c.colAddressId())
            .join(city, a.colCityId())
            .join(country, city.colCountryId())
            .findManyAsFlat()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(DemoApplication::class.java, JoinManyTables::class.java.name)
        }
    }
}
