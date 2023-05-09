package io.daobab.demo.example.part_c

import io.daobab.demo.DemoApplication
import io.daobab.demo.base.ServiceBase
import io.daobab.statement.function.FunctionWhispererH2
import org.springframework.boot.SpringApplication
import org.springframework.stereotype.Component

/**
 * ---------------------------------------------------------
 * Combined Functions
 * ---------------------------------------------------------
 */
@Component
class CombinedFunctions : ServiceBase<Int>(), FunctionWhispererH2 {
    override fun call(): Int =
        db.select(max(length(db.tabFilm.colDescription())))
            .whereEqual(db.tabFilm.colID(), 10)
            .findOne()


    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(DemoApplication::class.java, CombinedFunctions::class.java.name)
        }
    }
}
