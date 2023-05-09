package io.daobab.demo.example.part_c

import io.daobab.demo.DemoApplication
import io.daobab.demo.base.ServiceBase
import io.daobab.model.FlatPlate
import io.daobab.statement.function.FunctionWhispererH2
import org.springframework.boot.SpringApplication
import org.springframework.stereotype.Component

/**
 * ---------------------------------------------------------
 * Having
 * ---------------------------------------------------------
 */
@Component
class Having : ServiceBase<List<FlatPlate>>(), FunctionWhispererH2 {
    override fun call(): List<FlatPlate> {
        val c = db.tabFilm
        return db.select(c.colRating(), count(c.colID()).`as`("cnt"))
            .groupBy(c.colRating())
            .logQuery()
            .havingGreater("cnt", 200)
            .flat()
            .findMany()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(DemoApplication::class.java, Having::class.java.name)
        }
    }
}
