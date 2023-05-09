package io.daobab.demo.example.function

import io.daobab.demo.DemoApplication
import io.daobab.demo.base.ServiceBase
import io.daobab.demo.dao.table.FilmActor
import io.daobab.statement.function.FunctionWhispererH2
import org.springframework.boot.SpringApplication
import org.springframework.stereotype.Component

/**
 * ---------------------------------------------------------
 * Min
 * ---------------------------------------------------------
 */
@Component
class FunctionMin : ServiceBase<Int?>(), FunctionWhispererH2 {
    override fun call() :Int? = db.select(min(db.tabFilm.colLength()))
            .groupBy(db.tabFilm.colLanguageId())
            .findOne()


    fun call2(): FilmActor = db.selectRelated(db.tabFilm, db.tabFilmActor).findOne()


    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(DemoApplication::class.java, FunctionMin::class.java.name)
        }
    }
}
