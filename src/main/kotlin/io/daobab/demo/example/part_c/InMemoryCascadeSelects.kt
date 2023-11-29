package io.daobab.demo.example.part_c

import io.daobab.demo.DemoApplication
import io.daobab.demo.base.ServiceBase
import io.daobab.demo.dao.Lang
import io.daobab.demo.dao.table.Language
import io.daobab.model.Column
import org.springframework.boot.SpringApplication
import org.springframework.stereotype.Component

/**
 * ---------------------------------------------------------
 * In Memory Cascade Selects
 * ---------------------------------------------------------
 */
@Component
class InMemoryCascadeSelects : ServiceBase<List<String>>() {
    override fun call(): List<String> {
        val t = db.tabFilm
        val en = db.select(db.tabLanguage)
            .whereEqual(db.tabLanguage.colName(), Lang.English)
            .findOne()

        //all movies
        val allFilms = db.findAll(t)

        //english language all movies
        val englishMovies = allFilms.select(t).whereEqual(t.colLanguageId() as Column<Language, Int, Language>, en).findMany()

        //english language all movies having length longer than 150 minutes
        val longEnglishMovies = englishMovies.select(t)
            .whereGreater(t.colLength(), 150)
            .findMany()
        //just ordered titles of english language all movies having length longer than 150 minutes
        return longEnglishMovies.select(t.colTitle())
            .orderAscBy(t.colTitle())
            .findMany()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(DemoApplication::class.java, InMemoryCascadeSelects::class.java.name)
        }
    }
}
