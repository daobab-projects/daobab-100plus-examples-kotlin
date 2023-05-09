package io.daobab.demo.example.part_c

import io.daobab.demo.DemoApplication
import io.daobab.demo.base.ServiceBase
import io.daobab.demo.dao.Lang
import org.springframework.boot.SpringApplication
import org.springframework.stereotype.Component

/**
 * ---------------------------------------------------------
 * Select Native Many Cells
 * ---------------------------------------------------------
 */
@Component
class SelectNativeManyCells : ServiceBase<List<Lang>>() {

    override fun call(): List<Lang> =
        db.nativeSelect("select name from language order by 1 asc",
            db.tabLanguage.colName())
            .findMany()


    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(DemoApplication::class.java, SelectNativeManyCells::class.java.name)
        }
    }
}
