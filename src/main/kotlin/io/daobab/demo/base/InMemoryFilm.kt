package io.daobab.demo.base

import io.daobab.demo.dao.SakilaDataBase
import io.daobab.model.Entity
import io.daobab.target.buffer.multi.AboveMultiEntityTarget
import io.daobab.target.database.DataBaseTarget
import jakarta.annotation.PostConstruct
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class InMemoryFilm : AboveMultiEntityTarget() {
    @Autowired
    lateinit var db: SakilaDataBase

    @PostConstruct
    fun init() {
        register(db.tabFilm::class.java) //, Select.many(getSourceTarget(),tabFilm).where(tabFilm.colID(), LT,20));
        register(db.tabActor::class.java)
        register(db.tabFilmActor::class.java)
    }

    override fun getTables(): List<Entity> {
        return listOf(db.tabFilm, db.tabActor, db.tabFilmActor)
    }

    override fun getSourceTarget(): DataBaseTarget {
        return db
    }

}
