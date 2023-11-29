package io.daobab.demo.example.part_e

import io.daobab.demo.DemoApplication
import io.daobab.demo.base.ServiceBase
import io.daobab.demo.dao.column.AddressId
import io.daobab.model.Entity
import io.daobab.target.buffer.single.Entities
import org.springframework.boot.SpringApplication
import org.springframework.stereotype.Component

/**
 * ---------------------------------------------------------
 * Abstract Query Address Related Items
 * ---------------------------------------------------------
 * - How to build abstract query to apply specified type of Entities only
 */
@Component
class AbstractQueryAddressRelatedItems : ServiceBase<Unit>() {
    override fun call() {
        val adress = db.findOneByPk(db.tabAddress, 1)

        val customers = findAddressRelated(db.tabCustomer, adress)
        val stores = findAddressRelated(db.tabStore, adress)
        val staff = findAddressRelated(db.tabStaff, adress)
    }

    fun <V : Entity, E> findAddressRelated(
        entity: E,
        addressId: AddressId<V, Int>
    ): Entities<E> where E : Entity, E : AddressId<E, Int> =
        db.select(entity)
            .whereEqual(entity.colAddressId(), addressId.getAddressId())
            .findMany()

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(DemoApplication::class.java, AbstractQueryAddressRelatedItems::class.java.name)
        }
    }
}
