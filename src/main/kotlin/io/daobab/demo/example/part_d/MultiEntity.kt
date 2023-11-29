package io.daobab.demo.example.part_d

import io.daobab.demo.DemoApplication
import io.daobab.demo.base.ServiceBase
import io.daobab.parser.ParserGeneral
import io.daobab.statement.function.FunctionWhispererH2
import io.daobab.target.buffer.multi.MultiEntityTarget
import io.daobab.target.database.meta.MetaDataTables
import org.springframework.boot.SpringApplication
import org.springframework.stereotype.Component

/**
 * ---------------------------------------------------------
 * MultiEntityTarget
 * ---------------------------------------------------------
 */
@Component
class MultiEntity : ServiceBase<Unit>(), MetaDataTables, FunctionWhispererH2, ParserGeneral {
    override fun call() {

        //Building a multiTarget on three Entities
        val mt = MultiEntityTarget(
            db.select(db.tabFilmActor).whereEqual(db.tabFilmActor.colFilmId(), 1).findMany(),
            db.select(db.tabFilmCategory).whereEqual(db.tabFilmCategory.colFilmId(), 1).findMany(),
            db.select(db.tabFilmText).whereEqual(db.tabFilmText.colFilmId(), 1).findMany()
        )

        //queries...
        val cn1 = mt.select(count(db.tabFilmActor)).findOne()
        val cn2 = mt.select(count(db.tabFilmCategory)).findOne()
        val cn3 = mt.select(count(db.tabFilmText)).findOne()

        //results...
        log.info(toString(cn1))
        log.info(toString(cn2))
        log.info(toString(cn3))
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(DemoApplication::class.java, MultiEntity::class.java.name)
        }
    }
}
