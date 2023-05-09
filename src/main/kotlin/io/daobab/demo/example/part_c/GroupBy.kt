package io.daobab.demo.example.part_c

import io.daobab.demo.DemoApplication
import io.daobab.demo.base.ServiceBase
import io.daobab.result.FlatPlates
import io.daobab.statement.function.FunctionWhispererH2
import org.springframework.boot.SpringApplication
import org.springframework.stereotype.Component

/**
 * ---------------------------------------------------------
 * Group By
 * ---------------------------------------------------------
 */
@Component
class GroupBy : ServiceBase<FlatPlates>(), FunctionWhispererH2 {
    override fun call(): FlatPlates {

        val c = db.tabFilm
        return db.select(c.colRating(), count(c.colID()).`as`("count_groupBy"))
            .groupBy(c.colRating())
            .findManyAsFlat()
    }

    fun call2(): FlatPlates? {
        val c = db.tabFilm
        db.select(c.colRating(), count(c.colID()).`as`("count_groupBy"))
            .groupBy(c.colRating())
            .havingGreater(c.colLength(), 90)
            .findMany()
        return null
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(DemoApplication::class.java, GroupBy::class.java.name)
        }
    }
}
