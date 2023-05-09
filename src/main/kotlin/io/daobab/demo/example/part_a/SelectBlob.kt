package io.daobab.demo.example.part_a

import io.daobab.demo.DemoApplication
import io.daobab.demo.base.ServiceBase
import org.springframework.boot.SpringApplication
import org.springframework.stereotype.Component

/**
 * ---------------------------------------------------------
 * - How to use a blob
 * ---------------------------------------------------------
 */
@Component
class SelectBlob : ServiceBase<ByteArray?>() {
    override fun call() =
        db.select(db.tabStaff.colPicture())
            .whereEqual(db.tabStaff.colID(), 1)
            .findOne()

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(DemoApplication::class.java, SelectBlob::class.java.name)
        }
    }
}
