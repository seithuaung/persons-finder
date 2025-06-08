package com.persons.finder.domain.services

import com.persons.finder.data.Person
import java.util.*

interface PersonsService {
    fun getById(id: Long): Person
    fun getByIds(ids: Set<Long>) : List<Person>
    fun save(person: Person)
}