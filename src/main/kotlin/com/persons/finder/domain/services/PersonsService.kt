package com.persons.finder.domain.services

import com.persons.finder.domain.data.Person

interface PersonsService {
    fun getById(id: Long): Person
    fun getByIds(ids: Set<Long>) : List<Person>
    fun save(person: Person) : Person
}