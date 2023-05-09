package io.daobab.demo.example.part_b

import io.daobab.demo.DemoApplication
import io.daobab.demo.base.ServiceBase
import org.springframework.boot.SpringApplication
import org.springframework.stereotype.Component

/**
 * ---------------------------------------------------------
 * Accidental delete protection
 * ---------------------------------------------------------
 * Daobab requires a where clause for each delete and update
 */
@Component
class AccidentalDeleteProtection : ServiceBase<Int>() {
    override fun call() = db.delete(db.tabActor).execute()

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(DemoApplication::class.java, AccidentalDeleteProtection::class.java.name)
        }
    }
}
