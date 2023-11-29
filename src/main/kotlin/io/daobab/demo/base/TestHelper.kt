package io.daobab.demo.base

import io.daobab.model.Entity
import io.daobab.model.PrimaryKey
import io.daobab.target.buffer.single.Entities
import io.daobab.model.Plate
import io.daobab.target.buffer.single.Plates
import io.daobab.result.FlatPlates
import java.lang.Void
import org.slf4j.Logger
import java.util.function.Consumer

interface TestHelper {
    val log: Logger
    fun <E> validation(entity: E) where E : Entity?, E : PrimaryKey<*, *, *>? {
        check(entity)
        log.info("Result: entity " + entity!!.entityClass().simpleName)
        log.info(entity.toJson())
    }

    fun <E> validation(entity: Entities<E>) where E : Entity?, E : PrimaryKey<*, *, *>? {
        check(entity)
        log.info(entity.toJson())
    }

    fun validation(entity: Plate) {
        check(entity)
        log.info(entity.toJson())
    }

    fun validation(entity: Plates) {
        check(entity)
        log.info(entity.toJson())
    }

    fun validation(entity: FlatPlates) {
        check(entity)
        log.info(entity.toJson())
    }

    fun <F> validation(entity: List<F>) {
        check(entity)
        entity.forEach(Consumer { x: F -> println(x) })
    }

    fun <F> validation(entity: F) {
        check(entity)
        log.info(entity.toString())
    }

    fun <F> validation(entity: Void?) {
//        check(entity);
        log.info("result: expected null")
    }

    fun check(obj: Any?) {}
}
