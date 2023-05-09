package io.daobab.demo.example.part_c

import io.daobab.demo.DemoApplication
import io.daobab.demo.base.ServiceBase
import io.daobab.model.Plate
import io.daobab.statement.function.FunctionWhispererH2
import org.springframework.boot.SpringApplication
import org.springframework.stereotype.Component

/**
 * ---------------------------------------------------------
 * Select from dual
 * ---------------------------------------------------------
 * - Plate contains two counts. There is no table to select from.
 * For those cases, SQL provides a DUAL - dummy table, which is used
 * in the background.
 */
@Component
class SelectFromDual : ServiceBase<Plate?>(), FunctionWhispererH2 {

    override fun call(): Plate =
        db.select(
            db.select(count(db.tabFilm)).`as`("cntFilm"),
            db.select(count(db.tabCustomer)).`as`("cntCustomer")
        ).findOne()

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(DemoApplication::class.java, SelectFromDual::class.java.name)
        }
    }
}
