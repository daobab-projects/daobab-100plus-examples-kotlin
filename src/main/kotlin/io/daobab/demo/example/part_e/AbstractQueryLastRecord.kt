package io.daobab.demo.example.part_e

import io.daobab.demo.DemoApplication
import io.daobab.demo.base.ServiceBase
import io.daobab.model.Column
import io.daobab.model.Entity
import org.springframework.boot.SpringApplication
import org.springframework.stereotype.Component
import java.time.LocalDateTime

/**
 * ---------------------------------------------------------
 * Abstract query
 * ---------------------------------------------------------
 * - How to use abstract queries
 */
@Component
class AbstractQueryLastRecord : ServiceBase<Unit>() {
    override fun call() {
        val lastRecordFilm = findTheYoungestRecord(db.tabFilm.colLastUpdate())
        val lastRecordActor = findTheYoungestRecord(db.tabActor.colLastUpdate())
        val lastRecordRental = findTheYoungestRecord(db.tabRental.colLastUpdate())
        val lastlyCreatedCustomerRental = findTheYoungestRecord(db.tabCustomer.colCreateDate())
        val lastPayment = findTheYoungestRecord(db.tabPayment.colPaymentDate())
    }

    fun <E : Entity> findTheYoungestRecord(column: Column<E, LocalDateTime, *>): E =
        db.select(column.instance)
            .orderDescBy(column)
            .findOne()

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(DemoApplication::class.java, AbstractQueryLastRecord::class.java.name)
        }
    }
}
