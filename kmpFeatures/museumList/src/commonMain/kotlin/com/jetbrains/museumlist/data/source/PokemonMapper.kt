package com.jetbrains.museumlist.data.source

import com.jetbrains.museumlist.data.model.SimplePokemon
import com.jetbrains.room.database.PersonEntity


internal val SimplePokemon.toEntity: PersonEntity
    get() = PersonEntity(
        id = this.id,
        name = this.name,
    )

internal val PersonEntity.toModel: SimplePokemon
    get() = SimplePokemon(
        id = this.id,
        name = this.name,
    )