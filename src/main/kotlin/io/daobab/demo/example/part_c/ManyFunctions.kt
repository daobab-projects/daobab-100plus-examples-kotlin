package io.daobab.demo.example.part_c

import io.daobab.demo.DemoApplication
import io.daobab.demo.base.ServiceBase
import io.daobab.demo.example.part_c.ManyFunctions
import io.daobab.model.FlatPlate
import io.daobab.statement.function.FunctionWhispererH2
import org.springframework.boot.SpringApplication
import org.springframework.stereotype.Component

/**
 * ---------------------------------------------------------
 * Many Function
 * ---------------------------------------------------------
 * - How to use many sql functions
 */
@Component
class ManyFunctions : ServiceBase<FlatPlate?>(), FunctionWhispererH2 {

    override fun call(): FlatPlate {
        val f = db.tabFilm
        return db.select(
            sum(f.colLength()),
            avg(f.colLength()),
            min(f.colLength()),
            max(f.colLength()),
            count(f.colLength())
        )
            .flat()
            .findOne()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(DemoApplication::class.java, ManyFunctions::class.java.name)
        }
    }
}
