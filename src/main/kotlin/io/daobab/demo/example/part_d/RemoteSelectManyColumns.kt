package io.daobab.demo.example.part_d

import io.daobab.demo.DemoApplication
import io.daobab.demo.base.ServiceBase
import io.daobab.demo.example.part_d.RemoteSelectManyColumns
import io.daobab.target.buffer.single.Plates
import org.springframework.boot.SpringApplication
import org.springframework.stereotype.Component

/**
 * ---------------------------------------------------------
 * Remote Select Plates
 * ---------------------------------------------------------
 * - How to select Plates remotely
 */
@Component
class RemoteSelectManyColumns : ServiceBase<Plates>() {
    override fun call(): Plates {
        val t = db.tabFilm
        return remote.select(t.colTitle(), t.colDescription())
            .whereLess(t.colID(), 30)
            .orderDescBy(t.colTitle())
            .findMany()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(DemoApplication::class.java, RemoteSelectManyColumns::class.java.name)
        }
    }
}
