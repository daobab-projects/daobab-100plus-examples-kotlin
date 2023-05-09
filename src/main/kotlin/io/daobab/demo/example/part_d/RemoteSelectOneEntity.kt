package io.daobab.demo.example.part_d

import io.daobab.demo.DemoApplication
import io.daobab.demo.base.ServiceBase
import io.daobab.demo.dao.table.Film
import io.daobab.demo.example.part_d.RemoteSelectOneEntity
import org.springframework.boot.SpringApplication
import org.springframework.stereotype.Component

/**
 * ---------------------------------------------------------
 * Remote select One Entity
 * ---------------------------------------------------------
 * - How to select entity remotely
 */
@Component
class RemoteSelectOneEntity : ServiceBase<Film>() {
    override fun call() = way02()
    fun way01(): Film = remote.findOneByPk(db.tabFilm, 1)
    fun way02(): Film = remote.select(db.tabFilm).whereEqual(db.tabFilm.colID(), 1).findOne()

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(DemoApplication::class.java, RemoteSelectOneEntity::class.java.name)
        }
    }
}
