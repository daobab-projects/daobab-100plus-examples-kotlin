package io.daobab.demo.example.part_c

import io.daobab.demo.DemoApplication
import io.daobab.demo.base.ServiceBase
import io.daobab.demo.dao.Lang
import io.daobab.demo.dao.column.NameLang
import io.daobab.demo.dao.table.Film
import io.daobab.demo.dao.table.Language
import io.daobab.model.Column
import org.springframework.boot.SpringApplication
import org.springframework.stereotype.Component

/**
 * ---------------------------------------------------------
 * InMemory Inner Select
 * ---------------------------------------------------------
 * - How to use InMemory Inner Select as a part of normal SQL Query going to the DataBase.
 */
@Component
class InMemoryInnerSelect : ServiceBase<List<Film>>() {
    override fun call(): List<Film> {
        val l = db.tabLanguage
        val buffer = db.findAll(l)
        val t = db.tabFilm
        //Notice, the main query, going to real database, has inner select result inside
        return db.select(t)
            .whereIn(t.colLanguageId() as Column<Language, Int, Language>, buffer.select(l.colID()).whereIn(l.colName() as Column<Language, Lang, Language>, Lang.English, Lang.French))
            .findMany()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(DemoApplication::class.java, InMemoryInnerSelect::class.java.name)
        }
    }
}
