package io.daobab.demo.example.part_b

import io.daobab.demo.DemoApplication
import io.daobab.demo.base.ServiceBase
import io.daobab.demo.dao.table.Customer
import io.daobab.statement.function.FunctionWhispererH2
import io.daobab.target.database.query.frozen.DaoParam.param
import io.daobab.target.database.query.frozen.FrozenDataBaseQueryField
import jakarta.annotation.PostConstruct
import org.springframework.boot.SpringApplication
import org.springframework.stereotype.Component

/**
 * ---------------------------------------------------------
 * - How to create a frozenQuery with Parameters
 * ---------------------------------------------------------
 */
@Component
class FrozenQueryWithParameters : ServiceBase<List<String>>(), FunctionWhispererH2 {

    private var frozenQuery: FrozenDataBaseQueryField<Customer, String>? = null

    @PostConstruct
    fun init() {
        frozenQuery = db.select(db.tabCustomer.colFirstName())
            .whereLess(db.tabCustomer.colID(), param(1))
            .freezeQuery();
    }

    override fun call(): List<String> {
        return frozenQuery!!.withParameters(10).findMany()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(DemoApplication::class.java, FrozenQueryWithParameters::class.java.name)
        }
    }
}
