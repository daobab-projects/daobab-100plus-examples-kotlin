package io.daobab.demo.example.part_e

import io.daobab.demo.DemoApplication
import io.daobab.demo.base.ServiceBase
import io.daobab.demo.dao.table.enhanced.CountryCity
import io.daobab.target.buffer.single.Entities
import org.springframework.boot.SpringApplication
import org.springframework.stereotype.Component

/**
 * ---------------------------------------------------------
 * Embedded Columns
 * ---------------------------------------------------------
 * - How to use Embedded Columns (see into CounterCity entity)
 */
@Component
class EmbeddedColumns : ServiceBase<Entities<CountryCity>>() {
    override fun call(): Entities<CountryCity> {
        val c = CountryCity()
        return db.select(c)
            .whereEqual(c.colID(), 5)
            .findMany()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(DemoApplication::class.java, EmbeddedColumns::class.java.name)
        }
    }
}
