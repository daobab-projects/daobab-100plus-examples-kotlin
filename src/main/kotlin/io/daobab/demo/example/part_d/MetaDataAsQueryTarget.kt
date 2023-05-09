package io.daobab.demo.example.part_d

import io.daobab.demo.DemoApplication
import io.daobab.demo.base.ServiceBase
import io.daobab.demo.example.part_d.MetaDataAsQueryTarget
import io.daobab.target.database.connection.JdbcType
import io.daobab.target.database.meta.MetaDataTables
import org.springframework.boot.SpringApplication
import org.springframework.stereotype.Component

/**
 * ---------------------------------------------------------
 * MetaData as target
 * ---------------------------------------------------------
 */
@Component
class MetaDataAsQueryTarget : ServiceBase<Unit>(), MetaDataTables {
    override fun call() {
        val t = MetaDataTables.tabMetaColumn
        db.metaData
            .select(t.colTableColumnName(), t.colColumnSize())
            .whereEqual(t.colDatatype(), JdbcType.VARCHAR)
            .orderAscBy(t.colTableColumnName())
            .findMany()
            .forEach { log.info(it.toJSON()) }
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(DemoApplication::class.java, MetaDataAsQueryTarget::class.java.name)
        }
    }
}
