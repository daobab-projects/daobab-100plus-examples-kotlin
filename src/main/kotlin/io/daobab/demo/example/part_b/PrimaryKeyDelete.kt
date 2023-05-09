package io.daobab.demo.example.part_b

import io.daobab.demo.DemoApplication
import io.daobab.demo.base.ServiceBase
import org.springframework.boot.SpringApplication
import org.springframework.stereotype.Component

/**
 * ---------------------------------------------------------
 * - How to delete entity by PK method.
 * ---------------------------------------------------------
 */
@Component
class PrimaryKeyDelete : ServiceBase<Unit>() {
    override fun call() {
        db.findOneByPk(db.tabCategory, 11111).delete(db)
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(DemoApplication::class.java, PrimaryKeyDelete::class.java.name)
        }
    }
}
