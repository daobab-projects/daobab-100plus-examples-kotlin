package io.daobab.demo.example.part_c

import io.daobab.demo.DemoApplication
import io.daobab.demo.base.ServiceBase
import io.daobab.demo.dao.table.Category
import org.springframework.boot.SpringApplication
import org.springframework.stereotype.Component
import java.time.LocalDateTime

/**
 * ---------------------------------------------------------
 * Asynchronous Transaction wrapper
 * ---------------------------------------------------------
 * - How to wrap some DB operations into one transaction and don't wait for the execution end.
 */
@Component
class TransactionWrapperAsynchronous : ServiceBase<Unit>() {
    override fun call() {

        //Wrap all operation into single transaction
        db.wrapTransactionAsynch {
            //Insert entity
            val category = Category()
                .setLastUpdate(LocalDateTime.now())
                .setName("test")
                .insert(it)

            //Update entity
            category.setLastUpdate(LocalDateTime.now())
                .update(it, db.tabCategory.colLastUpdate())

            //Delete entity
            category.delete(it)
        }
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(DemoApplication::class.java, TransactionWrapperAsynchronous::class.java.name)
        }
    }
}
