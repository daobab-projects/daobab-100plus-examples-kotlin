package io.daobab.demo.example.part_d

import io.daobab.demo.DemoApplication
import io.daobab.demo.base.ServiceBase
import io.daobab.target.database.meta.MetaDataTables
import org.springframework.boot.SpringApplication
import org.springframework.stereotype.Component

/**
 * ---------------------------------------------------------
 * Get column datatype
 * ---------------------------------------------------------
 */
@Component
class MetaDataColumnDatatype : ServiceBase<Unit>(), MetaDataTables {
    override fun call(){
        val rs = db.metaData
            .getColumnDataType(db.tabFilm.colTitle())
        log.info(rs.toString())
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(DemoApplication::class.java, MetaDataColumnDatatype::class.java.name)
        }
    }
}
