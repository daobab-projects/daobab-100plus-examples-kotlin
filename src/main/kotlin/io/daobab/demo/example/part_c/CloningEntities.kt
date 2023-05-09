package io.daobab.demo.example.part_c

import io.daobab.demo.DemoApplication
import io.daobab.demo.base.ServiceBase
import org.springframework.boot.SpringApplication
import org.springframework.stereotype.Component

/**
 * ---------------------------------------------------------
 * Entities Cloning
 * ---------------------------------------------------------
 */
@Component
class CloningEntities : ServiceBase<Unit>() {
    override fun call(){
        val src = db.select(db.tabFilm)
            .whereLessOrEqual(db.tabFilm.colID(), 10)
            .findMany()
        val clone = src.clone()
        log.info("src size:" + src.size.toString() + ", clone size:" + clone.size)
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(DemoApplication::class.java, CloningEntities::class.java.name)
        }
    }
}
