package io.daobab.demo.example.part_d

import io.daobab.demo.DemoApplication
import io.daobab.demo.base.ServiceBase
import org.springframework.boot.SpringApplication
import org.springframework.stereotype.Component

/**
 * ---------------------------------------------------------
 * Remote Select Many Cells
 * ---------------------------------------------------------
 * - How to select many cells remotely
 */
@Component
class RemoteSelectManyCells : ServiceBase<List<String>>() {
    override fun call(): List<String> {
        val t = db.tabFilm
        return remote.select(t.colTitle())
            .whereLess(t.colID(), 30)
            .orderDescBy(t.colTitle())
            .findMany()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(DemoApplication::class.java, RemoteSelectManyCells::class.java.name)
        }
    }
}
