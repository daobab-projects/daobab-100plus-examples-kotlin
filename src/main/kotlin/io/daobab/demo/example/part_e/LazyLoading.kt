package io.daobab.demo.example.part_e

import io.daobab.demo.DemoApplication
import io.daobab.demo.base.ServiceBase
import io.daobab.demo.dao.table.Actor
import io.daobab.demo.dao.table.FilmActor
import io.daobab.demo.model.ActorLazyLoading
import org.springframework.boot.SpringApplication
import org.springframework.stereotype.Component

/**
 * ---------------------------------------------------------
 * Lazy Loading
 * ---------------------------------------------------------
 */
@Component
class LazyLoading : ServiceBase<List<ActorLazyLoading>>() {
    override fun call(): List<ActorLazyLoading> {
        val rv = db.select(db.tabFilmActor)
            .limitBy(3)
            .map { resultCompleter(it) }
            .findMany()

        //pick just one and call a method here to invoke a lazy loading on it
        val picked = rv[1]
        log.info(picked.actor!!.toJSON())
        return rv
    }

    private fun resultCompleter(filmActor: FilmActor): ActorLazyLoading {
        return object : ActorLazyLoading(filmActor) {
            override val actor: Actor?
                get() {
                    var rv = super.actor
                    if (rv == null) {
                        rv = db.select(db.tabActor).whereEqual(db.tabActor.colID(), filmActor.getActorId()).findOne()
                        setActor(rv)
                    }
                    return rv
                }
        }
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(DemoApplication::class.java, LazyLoading::class.java.name)
        }
    }
}
