package io.daobab.demo.example.part_a

import io.daobab.demo.DemoApplication
import io.daobab.demo.base.ServiceBase
import io.daobab.model.Column
import io.daobab.model.Plate
import io.daobab.target.buffer.single.Plates
import org.springframework.boot.SpringApplication
import org.springframework.stereotype.Component

/**
 * ---------------------------------------------------------
 * - How to get specified columns as a list
 * ---------------------------------------------------------
 */
@Component
class ManyPlatesManyTables : ServiceBase<Plates>() {
    override fun call() = way01()

    fun way01(): Plates {
        val t = db.tabCustomer
        val rv = db.select(t.colFirstName(), t.colLastName())
            .whereEqual(t.colActive(), true)
            .orderAscBy(t.colLastName())
            .findMany()
        logResult(rv, t.colFirstName(), t.colLastName())
        return rv
    }

    private fun logResult(plates: Plates, vararg col: Column<*, *, *>) {
        plates.forEach {
            logResult(it, *col)
        }
    }

    private fun logResult(plate: Plate, vararg col: Column<*, *, *>) {
        col.forEach {
            log.info("entity:" + it.entityName + ",column:" + it.columnName + ",value:" + plate.getValue(it))
        }
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(DemoApplication::class.java, ManyPlatesManyTables::class.java.name)
        }
    }
}
