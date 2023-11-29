package io.daobab.demo.example.part_b

import io.daobab.demo.DemoApplication
import io.daobab.demo.base.ServiceBase
import io.daobab.demo.dao.table.Customer
import io.daobab.statement.function.FunctionWhispererH2
import io.daobab.target.database.query.DataBaseQueryField
import io.daobab.target.database.query.frozen.DaoParam
import io.daobab.target.database.query.frozen.FrozenDataBaseQueryField
import jakarta.annotation.PostConstruct
import org.springframework.boot.SpringApplication
import org.springframework.stereotype.Component

/**
 * ---------------------------------------------------------
 * - How to unfreeze frozen Query
 * ---------------------------------------------------------
 */
@Component
class UnfreezeFrozenQuery : ServiceBase<List<String>>(), FunctionWhispererH2 {

    private var fistFrozenQuery: FrozenDataBaseQueryField<Customer, String>? = null

    private var secondFrozenQuery: FrozenDataBaseQueryField<Customer, String>? = null

    @PostConstruct
    fun init() {
        fistFrozenQuery = db.select(db.tabCustomer.colFirstName())
            .whereLess(db.tabCustomer.colID(), DaoParam.param(1))
            .freezeQuery()

        val unfrozenQuery: DataBaseQueryField<Customer, String> = fistFrozenQuery!!.unfreeze()

        val secondQuery: DataBaseQueryField<Customer, String> = unfrozenQuery.orderDescBy(db.tabCustomer.colFirstName())

        secondFrozenQuery = secondQuery.freezeQuery()
    }

    override fun call(): List<String> {
        return secondFrozenQuery!!.withParameters(10).findMany()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(DemoApplication::class.java, UnfreezeFrozenQuery::class.java.name)
        }
    }
}
