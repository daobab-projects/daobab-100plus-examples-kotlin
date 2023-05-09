package io.daobab.demo.example.part_c

import io.daobab.demo.DemoApplication
import io.daobab.demo.base.ServiceBase
import io.daobab.demo.dao.table.Category
import org.springframework.boot.SpringApplication
import org.springframework.stereotype.Component
import java.time.LocalDateTime

/**
 * ---------------------------------------------------------
 * Manual Transaction
 * ---------------------------------------------------------
 * - How to start, commit and rollback DB Operations having manual control on it.
 */
@Component
class TransactionManual : ServiceBase<Unit>() {
    override fun call(){

        //Open transaction manually
        val transaction = db.beginTransaction()
        try {
            //Insert entity
            val category = Category()
                .setLastUpdate(LocalDateTime.now())
                .setName("test")
                .insert(transaction)

            //Update entity
            category.setLastUpdate(LocalDateTime.now())
                .update(transaction, db.tabCategory.colLastUpdate())

            //Delete entity
            category.delete(transaction)
            transaction.commit()
        } catch (e: Exception) {
            transaction.rollback()
        }
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(DemoApplication::class.java, TransactionManual::class.java.name)
        }
    }
}
