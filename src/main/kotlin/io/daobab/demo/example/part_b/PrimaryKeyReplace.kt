package io.daobab.demo.example.part_b

import io.daobab.demo.DemoApplication
import io.daobab.demo.base.ServiceBase
import io.daobab.demo.dao.table.Category
import org.springframework.boot.SpringApplication
import org.springframework.stereotype.Component
import java.time.LocalDateTime

/**
 * ---------------------------------------------------------
 * - How to replace entity by PK method.
 *
 * WARNING: Replace may not work at H2 database
 * ---------------------------------------------------------
 */
@Component
class PrimaryKeyReplace : ServiceBase<Category>() {
    override fun call(): Category {
        return Category()
            .setLastUpdate(LocalDateTime.now())
            .setName("test")
            .replace(db)
    }

    fun way02(): Category {
        val category = Category()
            .setLastUpdate(LocalDateTime.now())
            .setName("test")

        return db.replace(category).execute()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(DemoApplication::class.java, PrimaryKeyReplace::class.java.name)
        }
    }
}
