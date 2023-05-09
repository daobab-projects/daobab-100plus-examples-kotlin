package io.daobab.demo.example.part_b

import io.daobab.demo.DemoApplication
import io.daobab.demo.base.ServiceBase
import io.daobab.demo.dao.table.Category
import io.daobab.demo.example.part_b.PrimaryKeyUpdate
import org.springframework.boot.SpringApplication
import org.springframework.stereotype.Component
import java.time.LocalDateTime

/**
 * ---------------------------------------------------------
 * - How to update entity by PK method.
 * ---------------------------------------------------------
 */
@Component
class PrimaryKeyUpdate : ServiceBase<Category>() {
    override fun call(): Category {
        return way01()
    }

    fun way01(): Category =
        db.findOneByPk(db.tabCategory, 1)
            .setLastUpdate(LocalDateTime.now())
            .update(db)


    fun way02(): Category {
        val category = Category()
            .setLastUpdate(LocalDateTime.now())
        db.update(category).execute()
        return category
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(DemoApplication::class.java, PrimaryKeyUpdate::class.java.name)
        }
    }
}
