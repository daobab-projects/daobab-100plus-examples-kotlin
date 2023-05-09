package io.daobab.demo.example.part_d

import io.daobab.demo.DemoApplication
import io.daobab.demo.base.ServiceBase
import io.daobab.query.base.QueryWhisperer
import org.springframework.boot.SpringApplication
import org.springframework.stereotype.Component

/**
 * ---------------------------------------------------------
 * Conditional Where
 * ---------------------------------------------------------
 */
@Component
class ConditionalWhere : ServiceBase<List<String>>(), QueryWhisperer {
    override fun call(): List<String> {
        return way01(byLength = true, byLanguage = true)
    }

    fun way01(byLength: Boolean, byLanguage: Boolean): List<String> {
        val t = db.tabFilm
        return db.select(t.colTitle())
            .where(and()
                .ifTrue(byLength) { it.greater(t.colLength(), 100) }
                .ifTrue(byLanguage) { it.equal(t.colLanguageId(), 1) })
            .orderDescBy(t.colTitle())
            .limitBy(20)
            .findMany()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(DemoApplication::class.java, ConditionalWhere::class.java.name)
        }
    }
}
