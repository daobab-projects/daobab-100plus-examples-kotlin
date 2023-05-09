package io.daobab.demo.example.part_c

import io.daobab.demo.DemoApplication
import io.daobab.demo.base.ServiceBase
import io.daobab.model.EntityMap
import org.springframework.boot.SpringApplication
import org.springframework.stereotype.Component
import java.time.LocalDateTime

/**
 * ---------------------------------------------------------
 * Entity Clone
 * ---------------------------------------------------------
 */
@Component
class CloningEntity : ServiceBase<Unit>() {
    override fun call() {
        val film1 = db.select(db.tabFilm)
            .whereEqual(db.tabFilm.colID(), 10)
            .findOne()
        val film2 = film1.clone()

        //make a difference:
        film2.setDescription("this is my change")
        film2.setLastUpdate(LocalDateTime.now().minusDays(10))
        checkClone(film1, film2)
    }

    private fun checkClone(source: EntityMap, clone: EntityMap) {
        log.info("Entity " + source.entityName + " has " + source.keys.size + " fields.")
        for (k in source.keys) {
            val objsrc = source[k]
            val objcln = clone[k]
            if (objsrc == null && objcln == null) continue
            if (objsrc == null && objcln == null == false) {
                log.warn("clone has no field $k")
            }
            if (objsrc == objcln == false) {
                log.warn("clone has different value of field $k")
            }
        }
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(DemoApplication::class.java, CloningEntity::class.java.name)
        }
    }
}
