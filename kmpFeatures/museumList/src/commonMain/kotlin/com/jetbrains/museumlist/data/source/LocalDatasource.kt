package com.jetbrains.museumlist.data.source

import com.jetbrains.museumlist.data.model.SimplePokemon
import com.jetbrains.room.database.PeopleDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update


interface LocalDatasource {
    suspend fun upsert(pokemon: SimplePokemon)
    suspend fun delete(pokemon: SimplePokemon)
    fun getAll(): Flow<List<SimplePokemon>>
}

class LocalDatasourceImpl(private val dao: PeopleDao) : LocalDatasource {
    override suspend fun upsert(pokemon: SimplePokemon) {
        dao.upsert(pokemon.toEntity)
    }

    override suspend fun delete(pokemon: SimplePokemon) {
        dao.delete(pokemon.toEntity)
    }

    override fun getAll(): Flow<List<SimplePokemon>> {
        return dao.getAllPeople().map { entities -> entities.map { it.toModel } }
    }
}


class FakeLocalDatasource : LocalDatasource {
    private val pokemonList = MutableStateFlow<List<SimplePokemon>>(emptyList())

    override suspend fun upsert(pokemon: SimplePokemon) {
        pokemonList.update { currentList ->
            val mutableList = currentList.toMutableList()
            val index = mutableList.indexOfFirst { it.id == pokemon.id }
            if (index != -1) {
                mutableList[index] = pokemon
            } else {
                mutableList.add(pokemon)
            }
            mutableList.toList()
        }
    }

    override suspend fun delete(pokemon: SimplePokemon) {
        pokemonList.update { currentList ->
            currentList.filterNot { it.id == pokemon.id }
        }
    }

    override fun getAll(): Flow<List<SimplePokemon>> {
        return pokemonList.asStateFlow()
    }
}