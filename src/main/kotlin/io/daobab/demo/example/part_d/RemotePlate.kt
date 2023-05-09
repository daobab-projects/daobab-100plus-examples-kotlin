package io.daobab.demo.example.part_d

import io.daobab.demo.DemoApplication
import io.daobab.demo.base.ServiceBase
import io.daobab.model.Plate
import org.springframework.boot.SpringApplication
import org.springframework.stereotype.Component

/**
 * ---------------------------------------------------------
 * Remote Select Plate
 * ---------------------------------------------------------
 * - How to select Plate remotely
 */
@Component
class RemotePlate : ServiceBase<Plate>() {
    override fun call(): Plate =
        remote.select(db.tabFilm.colTitle(), db.tabFilm.colDescription())
            .whereLess(db.tabFilm.colID(), 10)
            .findOne()

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(DemoApplication::class.java, RemotePlate::class.java.name)
        }
    }
}
