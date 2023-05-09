package io.daobab.demo.example.part_b

import io.daobab.demo.DemoApplication
import io.daobab.demo.base.ServiceBase
import io.daobab.demo.dao.table.Film
import io.daobab.parser.ParserGeneral
import org.springframework.boot.SpringApplication
import org.springframework.stereotype.Component
import java.time.LocalDate
import java.time.LocalDateTime

/**
 * ---------------------------------------------------------
 * Chained Setters and Insert
 * ---------------------------------------------------------
 */
@Component
class ChainedSettingAndInsert : ServiceBase<Film>(), ParserGeneral {

    override fun call(): Film {
        val film = Film()
            .setFilmId(3999)
            .setLastUpdate(LocalDateTime.now())
            .setDescription("Description")
            .setReleaseYear(LocalDate.now())
            .setReplacementCost(toBD(10.10))
            .setRentalDuration(10)
            .setRentalRate(toBD(1))
            .setLength(120)
            .setLanguageId(1)
            .setTitle("Test")
            .insert(db)
        film.delete(db)
        return film
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(DemoApplication::class.java, ChainedSettingAndInsert::class.java.name)
        }
    }
}
