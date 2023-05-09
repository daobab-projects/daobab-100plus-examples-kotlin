package io.daobab.demo.example.part_d

import io.daobab.demo.DemoApplication
import io.daobab.demo.base.ServiceBase
import io.daobab.parser.ParserGeneral
import io.daobab.statement.function.FunctionWhispererH2
import io.daobab.target.buffer.multi.QueryMultiEntityTarget
import io.daobab.target.database.meta.MetaDataTables
import org.springframework.boot.SpringApplication
import org.springframework.stereotype.Component

/**
 * ---------------------------------------------------------
 * QueryMultiEntityTarget
 * ---------------------------------------------------------
 */
@Component
class QueryMultiEntity : ServiceBase<Unit>(), MetaDataTables, FunctionWhispererH2, ParserGeneral {
    override fun call() {
        val mt = QueryMultiEntityTarget()
        mt.register(db.select(db.tabFilmActor).whereEqual(db.tabFilmActor.colFilmId(), 1))
        mt.register(db.select(db.tabFilmCategory).whereEqual(db.tabFilmCategory.colFilmId(), 1))
        mt.register(db.select(db.tabFilmText).whereEqual(db.tabFilmText.colFilmId(), 1))

        //queries...
        val c1 = mt.select(db.tabFilmActor).countAny()
        val c2 = mt.select(db.tabFilmCategory).countAny()
        val c3 = mt.select(db.tabFilmText).countAny()

        //results...
        log.info(toString(c1))
        log.info(toString(c2))
        log.info(toString(c3))
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(DemoApplication::class.java, QueryMultiEntity::class.java.name)
        }
    }
}
