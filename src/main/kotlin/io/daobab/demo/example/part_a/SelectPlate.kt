package io.daobab.demo.example.part_a

import io.daobab.demo.DemoApplication
import io.daobab.demo.base.ServiceBase
import io.daobab.model.Column
import io.daobab.model.Plate
import org.springframework.boot.SpringApplication
import org.springframework.stereotype.Component

/**
 * ---------------------------------------------------------
 * - How to get specific columns only. This example reads column from single row of particular table
 * ---------------------------------------------------------
 * - in this example, Plate may be easily replaced by related Entity.
 *
 * - Plate may be used for many table results, but always represents one row of data.
 */
@Component
class SelectPlate : ServiceBase<Plate>() {
    override fun call() = way01()

    //Only one (or fist) result can be returned
    fun way01(): Plate {
        val t = db.tabCustomer
        val rv = db.select(t.colFirstName(), t.colLastName())
            .whereEqual(t.colID(), 10)
            .findOne()
        logResult(rv, t.colFirstName(), t.colLastName())
        return rv
    }

    private fun logResult(plate: Plate, vararg col: Column<*, *, *>) {
        col.forEach {
            log.info(String.format("entity:%s ,column:%s ,value:%s", it.entityClass().simpleName, it.columnName, plate.getValue(it)))
        }
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(DemoApplication::class.java, SelectPlate::class.java.name)
        }
    }
}
