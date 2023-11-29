package io.daobab.demo.example.part_b

import io.daobab.demo.DemoApplication
import io.daobab.demo.base.ServiceBase
import io.daobab.demo.dao.table.Customer
import io.daobab.statement.function.FunctionWhispererH2
import io.daobab.target.database.query.frozen.FrozenDataBaseQueryField
import jakarta.annotation.PostConstruct
import org.springframework.boot.SpringApplication
import org.springframework.stereotype.Component

/**
 * ---------------------------------------------------------
 * - How to create a frozenQuery
 * ---------------------------------------------------------
 */
@Component
class FrozenQueryNoParameters : ServiceBase<List<String>>(), FunctionWhispererH2 {

    private var frozenQuery: FrozenDataBaseQueryField<Customer, String>? = null

    @PostConstruct
    fun init() {
        frozenQuery = db.select(db.tabCustomer.colFirstName())
            .whereLess(db.tabCustomer.colID(), 10).freezeQuery()
    }

    override fun call(): List<String> {
        return frozenQuery!!.findMany()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(DemoApplication::class.java, FrozenQueryNoParameters::class.java.name)
        }
    }
}
