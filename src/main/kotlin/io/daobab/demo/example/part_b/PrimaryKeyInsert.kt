package io.daobab.demo.example.part_b

import io.daobab.demo.DemoApplication
import io.daobab.demo.base.ServiceBase
import io.daobab.demo.dao.table.Category
import io.daobab.demo.example.part_b.PrimaryKeyInsert
import org.springframework.boot.SpringApplication
import org.springframework.stereotype.Component
import java.time.LocalDateTime

/**
 * ---------------------------------------------------------
 * - How to insert entity by PK method.
 * ---------------------------------------------------------
 */
@Component
class PrimaryKeyInsert : ServiceBase<Category>() {
    override fun call(): Category = Category()
        .setLastUpdate(LocalDateTime.now())
        .setName("test")
        .insert(db)


    fun way02(): Category {
        val category = Category()
            .setLastUpdate(LocalDateTime.now())
            .setName("test")
        return db.insert(category).execute()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(DemoApplication::class.java, PrimaryKeyInsert::class.java.name)
        }
    }
}
