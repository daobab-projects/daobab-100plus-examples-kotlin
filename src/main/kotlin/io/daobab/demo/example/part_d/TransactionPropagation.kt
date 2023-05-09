package io.daobab.demo.example.part_d

import io.daobab.demo.DemoApplication
import io.daobab.demo.base.ServiceBase
import io.daobab.demo.dao.table.Category
import io.daobab.demo.example.part_d.TransactionPropagation
import io.daobab.transaction.Propagation
import org.springframework.boot.SpringApplication
import org.springframework.stereotype.Component
import java.time.LocalDateTime

/**
 * ---------------------------------------------------------
 * Transaction Propagation
 * ---------------------------------------------------------
 * - How to propagate transactions
 */
@Component
class TransactionPropagation : ServiceBase<Unit>() {
    override fun call() {

        //Open transaction manually
        val transaction = db.beginTransaction()
        try {
            //Insert entity
            val category = Category()
                .setLastUpdate(LocalDateTime.now())
                .setName("test")
                .insert(transaction, Propagation.REQUIRED_NEW)

            //Update entity
            db.update(db.tabCategory.colLastUpdate(), LocalDateTime.now())
                .whereEqual(db.tabCategory.colCategoryId(), 1)
                .execute(Propagation.SUPPORTS)

            //Delete entity
            category.delete(transaction, Propagation.MANDATORY)
            transaction.commit()
        } catch (e: Exception) {
            transaction.rollback()
        }
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(DemoApplication::class.java, TransactionPropagation::class.java.name)
        }
    }
}
