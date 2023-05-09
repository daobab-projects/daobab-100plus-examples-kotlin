package io.daobab.demo.example.part_c

import io.daobab.demo.DemoApplication
import io.daobab.demo.base.ServiceBase
import io.daobab.demo.example.part_c.JoinLeft
import io.daobab.model.FlatPlate
import io.daobab.statement.join.JoinType
import org.springframework.boot.SpringApplication
import org.springframework.stereotype.Component

/**
 * ---------------------------------------------------------
 * Join Left
 * ---------------------------------------------------------
 * - How to use LEFT_JOIN or other join types
 */
@Component
class JoinLeft : ServiceBase<List<FlatPlate>>() {
    override fun call(): List<FlatPlate> {
        return way01()
            .onEach { log.info(it.toString()) }
    }

    fun way01(): List<FlatPlate> {
        val c = db.tabCustomer
        val a = db.tabAddress
        return db.select(c.colFirstName(), c.colLastName(), a.colPhone())
            .join(JoinType.LEFT_JOIN, a, c.colAddressId())
            .flat()
            .findMany()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(DemoApplication::class.java, JoinLeft::class.java.name)
        }
    }
}
