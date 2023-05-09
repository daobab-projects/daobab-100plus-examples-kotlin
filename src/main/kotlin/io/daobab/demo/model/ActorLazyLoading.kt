package io.daobab.demo.model

import io.daobab.demo.dao.table.FilmActor

open class ActorLazyLoading(filmActor: FilmActor) : FilmActor(), ActorLazy<FilmActor> {
    init {
        super.putAll(filmActor)
    }
}
