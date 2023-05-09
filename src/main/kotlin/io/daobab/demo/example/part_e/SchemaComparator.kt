package io.daobab.demo.example.part_e

import io.daobab.demo.DemoApplication
import io.daobab.demo.base.ServiceBase
import io.daobab.target.database.meta.MetaDataTables
import org.springframework.boot.SpringApplication
import org.springframework.stereotype.Component

/**
 * ---------------------------------------------------------
 * Schemas Comparator
 * ---------------------------------------------------------
 */
@Component
class SchemaComparator : ServiceBase<Unit>(), MetaDataTables {
    override fun call() {
        val tablesInSchema = db.tables.map { it.entityName }
        val columnsInSchema = db.tables
            .flatMap { it.columns() }
            .map { it.column }
            .map { it.entityName + "." + it.columnName }
        val notGeneratedTables = db.metaData
            .select(MetaDataTables.tabMetaTable.colTableName())
            .whereNotInFields(MetaDataTables.tabMetaTable.colTableName(), tablesInSchema)
            .findMany()
        val notGeneratedColumns = db.metaData
            .select(MetaDataTables.tabMetaColumn.colTableColumnName())
            .whereNotInFields(MetaDataTables.tabMetaColumn.colTableColumnName(), columnsInSchema)
            .orderAscBy(MetaDataTables.tabMetaColumn.colTableColumnName())
            .findMany()
        log.info(String.format("there is %s tables which are not generated.", notGeneratedTables.size))
        log.info(String.format("there is %s columns which are not generated.", notGeneratedColumns.size))
        if (notGeneratedTables.isEmpty() && notGeneratedColumns.isEmpty()) {
            log.info("The schema is fully compliant with the generated Daobab classes")
        }
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(DemoApplication::class.java, SchemaComparator::class.java.name)
        }
    }
}
