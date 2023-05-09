package io.daobab.demo.example.part_b

import io.daobab.demo.DemoApplication
import io.daobab.demo.base.ServiceBase
import io.daobab.demo.dao.table.Film
import io.daobab.demo.example.part_b.BuildInParsers
import io.daobab.parser.ParserGeneral
import io.daobab.query.base.QueryWhisperer
import org.springframework.boot.SpringApplication
import org.springframework.stereotype.Component
import java.time.LocalDate

/**
 * ---------------------------------------------------------
 * BuildIn Parsers
 * ---------------------------------------------------------
 */
@Component
class BuildInParsers : ServiceBase<List<Film>>(), QueryWhisperer, ParserGeneral {
    override fun call(): List<Film> {
        val f= db.tabFilm
        val id = 500L
        val amount = "10.02".toFloat()
        return db.select(f)
            .where(
                and()
                    .less(f.colFilmId(), toInteger(id))
                    .equal(f.colSpecialFeatures(), "Deleted Scenes")
                    .greater(f.colReplacementCost(), toBigDecimal(amount))
                    .greater(f.colLength(), toInteger("5"))
                    .less(f.colLastUpdate(), LocalDate.parse("2009-12-10").atStartOfDay())
            )
            .orderDescBy(f.colDescription())
            .findMany()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(DemoApplication::class.java, BuildInParsers::class.java.name)
        }
    }
}
