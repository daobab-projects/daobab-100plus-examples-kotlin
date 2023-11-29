package io.daobab.demo.example.part_b

import io.daobab.demo.DemoApplication
import io.daobab.demo.base.ServiceBase
import io.daobab.demo.dao.column.ActorId
import io.daobab.demo.dao.column.Description
import io.daobab.demo.dao.column.FilmId
import io.daobab.demo.dao.table.FilmActor
import io.daobab.demo.dao.table.FilmActorKey
import io.daobab.model.Table
import io.daobab.model.TableColumn
import org.springframework.boot.SpringApplication
import org.springframework.stereotype.Component
import java.util.*

/**
 * ---------------------------------------------------------
 * Select Native Plate
 * ---------------------------------------------------------
 */
@Component
class SelectByCompositeKey : ServiceBase<FilmActor>() {
    override fun call(): FilmActor {
        val anotherEntity = AnotherEntityHavingTheSameCompositeKeyColumns()
            .setActorId(1).setFilmId(1).setDescription("A description")
        return db.select(db.tabFilmActor)
            .whereEqual(db.tabFilmActor.colCompositeId(), anotherEntity)
            .findOne()
    }

    internal class AnotherEntityHavingTheSameCompositeKeyColumns :
        Table<AnotherEntityHavingTheSameCompositeKeyColumns>(),
        FilmActorKey<AnotherEntityHavingTheSameCompositeKeyColumns>,
        ActorId<AnotherEntityHavingTheSameCompositeKeyColumns, Int>,
        FilmId<AnotherEntityHavingTheSameCompositeKeyColumns, Int>,
        Description<AnotherEntityHavingTheSameCompositeKeyColumns, String> {
        override fun columns(): List<TableColumn> {
            return listOf(
                TableColumn(colActorId()).primaryKey().size(5),
                TableColumn(colFilmId()).primaryKey().size(5),
                TableColumn(colDescription()).primaryKey().size(50)
            )
        }
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(DemoApplication::class.java, SelectByCompositeKey::class.java.name)
        }
    }
}
