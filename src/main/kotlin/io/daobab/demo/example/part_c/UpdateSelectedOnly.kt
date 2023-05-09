package io.daobab.demo.example.part_c

import io.daobab.demo.DemoApplication
import io.daobab.demo.base.ServiceBase
import io.daobab.demo.example.part_c.UpdateSelectedOnly
import io.daobab.query.base.QueryWhisperer
import org.springframework.boot.SpringApplication
import org.springframework.stereotype.Component
import java.time.LocalDateTime

/**
 * ---------------------------------------------------------
 * PK - Update selected only
 * ---------------------------------------------------------
 * - How to use PK method to update just selected columns
 */
@Component
class UpdateSelectedOnly : ServiceBase<Unit>(), QueryWhisperer {
    override fun call() {
        way01()
    }

    fun way01() {
        val category = db.findOneByPk(db.tabCategory, 1)
        category.setLastUpdate(LocalDateTime.now())
        //update single column only
        category.update(db, db.tabCategory.colLastUpdate())
    }

    fun way02() {
        db.update(db.tabCategory.colLastUpdate(), LocalDateTime.now())
            .whereEqual(db.tabCategory.colCategoryId(), 1)
            .execute()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(DemoApplication::class.java, UpdateSelectedOnly::class.java.name)
        }
    }
}
