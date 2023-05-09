package io.daobab.demo.example.part_d

import io.daobab.demo.DemoApplication
import io.daobab.demo.base.ServiceBase
import io.daobab.demo.example.part_d.JoinWithJoinConditions
import io.daobab.model.FlatPlate
import io.daobab.query.base.QueryWhisperer
import org.springframework.boot.SpringApplication
import org.springframework.stereotype.Component

/**
 * ---------------------------------------------------------
 * Join Table With Join Conditions
 * ---------------------------------------------------------
 * - How to join tables with where conditions attached
 */
@Component
class JoinWithJoinConditions : ServiceBase<List<FlatPlate>>(), QueryWhisperer {
    override fun call(): List<FlatPlate> {
        val rv = way01()
        rv.forEach { log.info(it.toString()) }
        return rv
    }

    fun way01(): List<FlatPlate> {
        val c = db.tabCustomer
        val a = db.tabAddress
        val city = db.tabCity
        val country = db.tabCountry
        return db.select(
            c.colFirstName(), c.colLastName(), a.colPhone(), city.colCity(), country.colCountry()
        )
            .join(a, c.colAddressId(), and().equal(c.colActive(), true))
            .join(city, a.colCityId(), and().like(city.colCity(), "%a%"))
            .join(country, city.colCountryId(), and().`in`(country.colCountry(), "United States", "Russian Federation"))
            .findManyAsFlat()
    }

    fun way02(): List<FlatPlate> {
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
            .whereAnd {
                it
                    .equal(c.colActive(), true)
                    .like(city.colCity(), "%a%")
                    .`in`(country.colCountry(), "United States", "Russian Federation")
            }
            .flat()
            .findMany()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(DemoApplication::class.java, JoinWithJoinConditions::class.java.name)
        }
    }
}
