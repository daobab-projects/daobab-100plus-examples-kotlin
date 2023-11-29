package io.daobab.demo.example.part_c

import io.daobab.demo.DemoApplication
import io.daobab.demo.base.ServiceBase
import io.daobab.demo.dao.table.Category
import io.daobab.transaction.Propagation
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
class TransactionWrapperVoid : ServiceBase<Unit>() {
    override fun call() {
        val cat = Category()
            .setLastUpdate(LocalDateTime.now())
            .setName("test")
        val anotherCat = db.select(db.tabCategory).whereEqual(db.tabCategory.colID(), 1).findOne()
        anotherCat.setName("anotherName")
        db.wrapTransaction {
            it.insert(cat).execute()
            it.update(anotherCat).execute(Propagation.REQUIRED_NEW)
        }


        //Wrap all operation into single transaction
        db.wrapTransaction {
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
            SpringApplication.run(DemoApplication::class.java, TransactionWrapperVoid::class.java.name)
        }
    }
}
