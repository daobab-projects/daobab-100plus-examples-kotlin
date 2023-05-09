package io.daobab.demo.example.part_a

import io.daobab.demo.DemoApplication
import io.daobab.demo.base.ServiceBase
import io.daobab.demo.dao.Lang
import org.springframework.boot.SpringApplication
import org.springframework.stereotype.Component

/**
 * ---------------------------------------------------------
 * - How to use Enums
 * ---------------------------------------------------------
 */
@Component
class Enums : ServiceBase<List<Lang>>() {
    override fun call(): List<Lang> =
        db.select(db.tabLanguage.colName())
            .orderAscBy(db.tabLanguage.colName())
            .findMany()


    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(DemoApplication::class.java, Enums::class.java.name)
        }
    }
}
