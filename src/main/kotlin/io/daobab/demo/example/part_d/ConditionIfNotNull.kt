package io.daobab.demo.example.part_d

import io.daobab.demo.DemoApplication
import io.daobab.demo.base.ServiceBase
import io.daobab.demo.example.part_d.ConditionIfNotNull
import io.daobab.query.base.QueryWhisperer
import io.daobab.statement.condition.Operator
import org.springframework.boot.SpringApplication
import org.springframework.stereotype.Component

/**
 * ---------------------------------------------------------
 * Condition If Not Null
 * ---------------------------------------------------------
 * - How to use Conditional Where If Not Null
 */
@Component
class ConditionIfNotNull : ServiceBase<List<String>>(), QueryWhisperer {

    override fun call(): List<String> {
        val t = db.tabFilm
        return db.select(t.colTitle())
            .where(
                and()
                    .ifNotNull(t.colLanguageId(), Operator.EQ, 1)
            )
            .orderDescBy(t.colTitle())
            .limitBy(20)
            .findMany()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(DemoApplication::class.java, ConditionIfNotNull::class.java.name)
        }
    }
}
