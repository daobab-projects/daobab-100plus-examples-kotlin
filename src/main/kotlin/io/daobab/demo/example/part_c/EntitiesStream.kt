package io.daobab.demo.example.part_c

import io.daobab.demo.DemoApplication
import io.daobab.demo.base.ServiceBase
import io.daobab.demo.dao.Lang
import org.springframework.boot.SpringApplication
import org.springframework.stereotype.Component

/**
 * ---------------------------------------------------------
 * Entities Stream
 * ---------------------------------------------------------
 * - Entities is a collection, so stream operations available as well.
 */
@Component
class EntitiesStream : ServiceBase<List<String>>() {
    override fun call(): List<String> {
        val en = db.select(db.tabLanguage)
            .whereEqual(db.tabLanguage.colName(), Lang.English)
            .findOne()
        return db.findAll(db.tabFilm)
            .filter { it.getLanguageId() == en.getLanguageId() }
            .filter { it.getLength()!! > 150 }
            .map { it.getTitle() }
    }

    fun way02(): List<String> {
        val en = db.select(db.tabLanguage)
            .whereEqual(db.tabLanguage.colName(), Lang.English)
            .findOne()
        val t = db.tabFilm
        return db.findAll(t)
            .filter { it.getLanguageId() == en.getLanguageId() }
            .filter { it.getLength()!! > 150 }
            .map { it.getTitle() }
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(DemoApplication::class.java, EntitiesStream::class.java.name)
        }
    }
}
