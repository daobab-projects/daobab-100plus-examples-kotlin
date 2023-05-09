package io.daobab.demo.example.part_c

import io.daobab.demo.DemoApplication
import io.daobab.demo.base.ServiceBase
import io.daobab.demo.dao.table.Category
import org.springframework.boot.SpringApplication
import org.springframework.stereotype.Component
import java.time.LocalDateTime

/**
 * ---------------------------------------------------------
 * Transaction wrapper
 * ---------------------------------------------------------
 * - How to wrap some DB operations into one transaction
 */
@Component
class TransactionWrapper : ServiceBase<Unit>() {
    override fun call() {

        //Wrap all operation into single transaction
        val deletedRecords = db.wrapTransaction {
            //Insert entity
            val category = Category()
                .setLastUpdate(LocalDateTime.now())
                .setName("test")
                .insert(it)

            //Update entity
            category.setLastUpdate(LocalDateTime.now())
                .update(it, db.tabCategory.colLastUpdate())
            category.delete(it)
        }
        log.info("deleted records:$deletedRecords")
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(DemoApplication::class.java, TransactionWrapper::class.java.name)
        }
    }
}
